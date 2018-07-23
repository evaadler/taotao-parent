package com.taotao.controller;

import com.taotao.service.PictureService;
import com.taotao.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map pictureUpload(MultipartFile uploadFile) {
        Map result = pictureService.uploadPicture(uploadFile);

        // 为了保证功能兼容性，把Result转换为json格式的字符串
        String json  = JsonUtils.objectToJson(result);
        return result;
    }
}
