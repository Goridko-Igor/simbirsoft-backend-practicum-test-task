package ru.goridko_igor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.goridko_igor.model.HtmlParserResult;
import ru.goridko_igor.service.HtmlParserService;

@Controller
@RequestMapping("/html-parser")
public class HtmlParserController {
    private final HtmlParserService htmlParserService;

    @Autowired
    public HtmlParserController(HtmlParserService htmlParserService) {
        this.htmlParserService = htmlParserService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("htmlParserResult", new HtmlParserResult());
        return "html-parser/index";
    }

    @PostMapping("/result")
    public String parseHtml(
            @ModelAttribute("htmlParserResult") HtmlParserResult htmlParserResult,
            Model model) {
        htmlParserResult = htmlParserService.parseHtml(htmlParserResult);
        model.addAttribute("htmlParserResult", htmlParserResult);
        return "html-parser/result";
    }
}
