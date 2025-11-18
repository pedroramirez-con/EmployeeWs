/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfig {

    private static final Logger log = LoggerFactory.getLogger(LoggingConfig.class);

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true); 
        loggingFilter.setMaxPayloadLength(10000);
        
        // Incluir Headers
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setHeaderPredicate(headerName -> 
            headerName.equalsIgnoreCase("Authorization") || 
            headerName.equalsIgnoreCase("User-Agent") ||
            headerName.equalsIgnoreCase("X-Request-ID") 
        );
        
        loggingFilter.setAfterMessagePrefix("REQUEST DATA: ");
        
        log.info("CommonsRequestLoggingFilter configurado para loggear headers y payload.");
        return loggingFilter;
    }
}