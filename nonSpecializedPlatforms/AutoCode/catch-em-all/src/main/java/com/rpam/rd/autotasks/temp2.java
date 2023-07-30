package com.epam.storefront.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import groovy.util.logging.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.ws.rs.NotAuthorizedException;

@Slf4j
@Controller
@RequestMapping("/telegram")
public class TelegramGetRequestsController {

    @Resource
    private com.epam.storefront.controllers.CsTelegramVerificationService csTelegramVerificationService;

    private static final Logger logger = LoggerFactory.getLogger(TelegramGetRequestsController.class);

    @GetMapping("/verification")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void getRequestsFromTelegramBot(@RequestParam(value = "chatid", required = false) String chatId,
                                           @RequestParam(value = "token", required = false) String token
    ) {

        try {
            csTelegramVerificationService.verifyUserBySecureToken(token, chatId);
        } catch (Exception e) {
            if ((e instanceof NotAuthorizedException)) {
                logger.info("VerificationFailed for Anonymous User");
            } else {
                System.err.println(e.getMessage());
            }
        }

    }
}