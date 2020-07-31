package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.ivashchuk.employerresearcher.domain.CoverLetter;
import pro.ivashchuk.employerresearcher.repository.JpaCoverLetterRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/coverLetters")
public class CoverLetterController {

    @Autowired
    private JpaCoverLetterRepository jpaCoverLetterRepository;

    @GetMapping
    public String getAllCoverLetters(Model model) {
        List<CoverLetter> coverLetters = new ArrayList<CoverLetter>(jpaCoverLetterRepository.findAll());
        Collections.sort(coverLetters);
        model.addAttribute("coverLetters", coverLetters);
        return "all_cover_letters";
    }
}
