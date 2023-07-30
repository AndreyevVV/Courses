package com.epam.storefront.controllers;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.NotAuthorizedException;

@Component
public class CsTelegramVerificationService {

    @Resource
    private UserService userService;

    @Resource
    private ModelService modelService;

    public void verifyUserBySecureToken(final String secureToken, final String chatid) throws NotAuthorizedException {

        UserModel currentUser = userService.getCurrentUser();

        if (!userService.isAnonymousUser(currentUser)) {

            CustomerModel customerModel = (CustomerModel) currentUser;

            String currentCustomerToken = customerModel.getTelegramToken();
            String currentCustomerTelegramId = customerModel.getTelegramId();

            boolean isTelegramUser = currentCustomerTelegramId != null && currentCustomerTelegramId.equals(chatid);
            boolean isVerifiedBySecureToken = currentCustomerToken.equals(secureToken);

// First time user verification (User has token but didn't use telegram)
            if ( isVerifiedBySecureToken && !isTelegramUser) {
                customerModel.setTelegramId(chatid);
                modelService.save(customerModel);
            }
// Verified User;
        }
// return VerificationFailed;
        throw new NotAuthorizedException("VerificationFailed for Anonymous User");
    }

}
