package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.ivashchuk.employerresearcher.repository.JpaResumeRepository;

@Controller
@RequestMapping("/resumes")
public class ResumeController {

    @Autowired
    private JpaResumeRepository jpaResumeRepository;
}
