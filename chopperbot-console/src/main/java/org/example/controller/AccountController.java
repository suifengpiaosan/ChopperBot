package org.example.controller;


import org.apache.tools.ant.taskdefs.Get;
import org.example.core.pojo.Account;
import org.example.core.pojo.AccountVO;
import org.example.pojo.GPTKey;
import org.example.service.AccountService;
import org.example.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/12 22:26
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    AccountService accountService;

    @GetMapping(value = "/getUser/{platformId}")
    public Result getAllUser(@PathVariable int platformId){
        List<AccountVO> list = accountService.getAllUser(platformId);
        return Result.success(list);
    }

    @GetMapping(value = "/getUser")
    public Result getAllUser(){
        return Result.success(accountService.getAllUser());
    }

    @PostMapping(value = "/login/{platformId}")
    public Result login(@RequestBody String username, @RequestBody String password, @PathVariable int platformId){
        accountService.login(platformId,username,password);
        return Result.success();
    }

    @PostMapping(value = "/edit")
    public Result edit(@RequestBody Account account){
        accountService.edit(account);
        return  Result.success();
    }

    @GetMapping(value = "/gpt/key")
    public Result getGPT(){
        return Result.success(Map.of("key",accountService.chatGptPlugin().getKey()));
    }

    @PostMapping(value = "/gpt/add")
    public Result addGPT(@RequestBody GPTKey key){
        boolean b = accountService.chatGptPlugin().addKey(key);
        return Result.success(Map.of("success",b));
    }

    @PostMapping(value="/gpt/update")
    public Result updateGPT(@RequestBody GPTKey key){
        boolean b = accountService.chatGptPlugin().changeKey(key);
        return Result.success(Map.of("success",b));
    }
}
