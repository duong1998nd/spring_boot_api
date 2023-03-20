package com.locShop.securityConfig;

import com.locShop.controller.Authentication;

public interface AppAuthorizer  {

    boolean authorize(Authentication authentication, String action, Object callerObj);
}
