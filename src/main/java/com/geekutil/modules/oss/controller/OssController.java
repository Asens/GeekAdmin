package com.geekutil.modules.oss.controller;

import com.geekutil.common.util.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Asens
 * create 2019-08-18 9:50
 **/
@RestController
@RequestMapping("/api/oss")
public class OssController {
    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file) {
        //Fake interface
        return Result.success()
                .data("https://img.asens.cn/images/note/1/as148668994644749.jpg");
    }
}
