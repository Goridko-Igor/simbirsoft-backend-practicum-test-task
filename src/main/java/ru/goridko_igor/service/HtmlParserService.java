package ru.goridko_igor.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import ru.goridko_igor.exception.HtmlPageNotFoundException;
import ru.goridko_igor.model.HtmlParserResult;
import ru.goridko_igor.model.WordCount;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HtmlParserService {
    public HtmlParserResult parseHtml(HtmlParserResult htmlParserResult) throws HtmlPageNotFoundException {
        try {
            String htmlPageUrlAddress = htmlParserResult.getUrlAddress();
            Document htmlPage = Jsoup.connect(htmlPageUrlAddress).timeout(5000).get();
            Map<String, Long> wordsStatistics = getWordsStatistics(htmlPage);
            wordsStatistics.forEach((word, count) -> htmlParserResult.getWordsCount().add(new WordCount(word, count)));
            htmlParserResult.getWordsCount().sort(
                    (wordCount1, wordCount2) -> wordCount2.getCount().compareTo(wordCount1.getCount()));
        } catch (IOException e) {
            throw new HtmlPageNotFoundException("HTML-страница не найдена!", e);
        }

        return htmlParserResult;
    }

    private Map<String, Long> getWordsStatistics(Document htmlPage) {
        return htmlPage.body().getAllElements().stream()
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
    }
}
