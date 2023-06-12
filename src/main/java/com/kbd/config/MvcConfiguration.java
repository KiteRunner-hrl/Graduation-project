package com.kbd.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("com.kbd.controller")
@Configuration
@EnableWebMvc
public class MvcConfiguration {
}
