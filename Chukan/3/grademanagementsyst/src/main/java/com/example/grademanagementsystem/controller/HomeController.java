package com.example.grademanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * トップ画面を制御するコントローラー
 */
@Controller
public class HomeController {

    /**
     * トップ画面を表示する
     *
     * @return トップ画面
     */
    @GetMapping("/")
    public String showHome() {
        return "index";
    }
}