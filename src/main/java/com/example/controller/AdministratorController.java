package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

/**
 * 管理者情報を操作するコントローラー.
 * 
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private HttpSession session;

    @ModelAttribute
    public InsertAdministratorForm setUpInsertAdministratorForm() {
        return new InsertAdministratorForm();
    }

    /**
     * 管理者登録画面を出力します.
     * 
     * @return 管理者登録画面
     */
    @GetMapping("/toInsert")
    public String toInsert() {
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録します.
     * 
     * @param form 管理者情報用フォーム
     * @return ログイン画面へリダイレクト
     */
    @PostMapping("/insert")
    public String insert(@Validated InsertAdministratorForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "administrator/insert";
        }

        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        
        return "redirect:/employee/showList";
    }
}
