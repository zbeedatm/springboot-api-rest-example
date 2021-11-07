package com.github.throyer.common.springboot.controllers.api;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.github.throyer.common.springboot.domain.services.user.RecoveryPasswordService;
import com.github.throyer.common.springboot.domain.services.user.dto.RecoveryConfirm;
import com.github.throyer.common.springboot.domain.services.user.dto.RecoveryRequest;
import com.github.throyer.common.springboot.domain.services.user.dto.RecoveryUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "Password recovery", produces = "application/json")
@RestController
@RequestMapping("/api/recoveries")
public class RecoveriesController {

    @Autowired
    private RecoveryPasswordService service;

    @PostMapping
    @ResponseStatus(NO_CONTENT)
    public void index(@RequestBody RecoveryRequest request) {        
        service.recovery(request.getEmail());
    }

    @PostMapping("/confirm")
    public void confirm(@RequestBody RecoveryConfirm confirm) {        
        service.confirm(confirm.getEmail(), confirm.getCode());
    }

    @PostMapping("/update")
    @ResponseStatus(NO_CONTENT)
    public void update(@RequestBody RecoveryUpdate update) {        
        service.update(update.getEmail(), update.getCode(), update.getPassword());
    }
}
