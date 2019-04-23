package com.shop.myshop.shiroRealm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//        String username = (String) token.getPrincipal();
        return super.doCredentialsMatch(token, info);
    }

    public RetryLimitHashedCredentialsMatcher() {
    }
}
