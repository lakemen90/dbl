package com.j.dbl.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create date 2018-10-31 17:26
 * <p></p>
 *
 * @author 江斌(bin.jiang @ ucarinc.com)
 * @since 1.0.
 **/
@Controller
public class FePageController {

    @RequestMapping("fe/{url}.html")
    public String module(@PathVariable("url") String url){
        return "fe" + "/" + url;
    }



}
