package ru.goridko_igor.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import ru.goridko_igor.model.HtmlParserResult;
import ru.goridko_igor.model.WordCount;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HtmlParserService {
    public HtmlParserResult parseHtml(HtmlParserResult htmlParserResult) {
        try {
            String htmlPageUrlAddress = htmlParserResult.getUrlAddress();
            Document htmlPage = Jsoup.connect(htmlPageUrlAddress).timeout(5000).get();

            Map<String, Long> wordsStatistics = htmlPage.body().getAllElements().stream()
                    .map(Element::ownText)
                    .filter(tagText -> !tagText.isBlank())
                    .map(tagText -> tagText
                            .replaceAll("‑", "-")
                            .replaceAll("(\\s-\\s)", " ")
                            .replaceAll("[^a-zA-Zа-яА-ЯёЁ0-9-]", " ")
                            .trim()
                            .replaceAll("(\\s)+", "$1")
                            .toUpperCase()
                    )
                    .flatMap(preparedTagText -> Stream.of(preparedTagText.split(" ")))
                    .toList()
                    .stream()
                    .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

            List<WordCount> wordsCount = htmlParserResult.getWordsCount();
            wordsStatistics.forEach((word, count) -> wordsCount.add(new WordCount(word, count)));
            wordsCount.sort((wordCount1, wordCount2) -> wordCount2.getCount().compareTo(wordCount1.getCount()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return htmlParserResult;
    }
}
