package com.walter.spbt.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.walter.spbt.bean.SpbtResponseEntity;
import com.walter.spbt.utils.ResponseUtils;

import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = {"Markdown-markdown转html"})
@RequestMapping("markdown")
@Slf4j
public class MdToHtmlController {

    @Value("${server.port}")
    private String port;

    private static final String FILE_PATH = "static/vuepress/mdfiles/";
    private static final String FILE_NAME = "fileName";
    private static final String FILE_ADRESS = "fileAddress";

    @SneakyThrows
    @GetMapping("mdfile")
    @ResponseBody
    public SpbtResponseEntity getMarkdownFile() {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        this.getClass().getClassLoader().getResourceAsStream("/static/static/vuepress/mdfiles/")));
        List<String> fileNameList=new ArrayList<>();
        String line;
        while ((line = in.readLine()) != null){
            fileNameList.add(line);
        }
        List<Map<String, String>> linkList = new ArrayList<>();
        fileNameList.forEach(e -> {
            Map<String, String> map = new HashMap<>();
            map.put(FILE_NAME, e);
            map.put(FILE_ADRESS, FILE_PATH + e);
            linkList.add(map);
        });
        return ResponseUtils.successResponse(linkList);
    }
}
