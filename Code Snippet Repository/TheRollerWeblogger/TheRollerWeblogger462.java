    public void init(FilterConfig filterConfig) {

        // determine if we are doing scheme enforcement
        this.schemeEnforcementEnabled = WebloggerConfig
                .getBooleanProperty("schemeenforcement.enabled");
        this.secureLoginEnabled = WebloggerConfig
                .getBooleanProperty("securelogin.enabled");

        if (this.schemeEnforcementEnabled && this.secureLoginEnabled) {
            // gather some more properties
            String http_port = WebloggerConfig
                    .getProperty("securelogin.http.port");
            String https_port = WebloggerConfig
                    .getProperty("securelogin.https.port");

            try {
                this.httpPort = Integer.parseInt(http_port);
                this.httpsPort = Integer.parseInt(https_port);
            } catch (NumberFormatException nfe) {
                // ignored ... guess we'll have to use the defaults
                log.warn("error with secure login ports", nfe);
            }

            // finally, construct our list of allowable https urls and ignored
            // resources
            String cfgs = WebloggerConfig
                    .getProperty("schemeenforcement.https.urls");
            String[] cfgsArray = cfgs.split(",");
            Collections.addAll(this.allowedUrls, cfgsArray);

            cfgs = WebloggerConfig
                    .getProperty("schemeenforcement.https.ignored");
            cfgsArray = StringUtils.stripAll(StringUtils.split(cfgs, ","));
            Collections.addAll(this.ignored, cfgsArray);

            // some logging for the curious
            log.info("Scheme enforcement = enabled");
            if (log.isDebugEnabled()) {
                log.debug("allowed urls are:");
                for (String allowedUrl : allowedUrls) {
                    log.debug(allowedUrl);
                }
                log.debug("ignored extensions are:");
                for (String ignore : ignored) {
                    log.debug(ignore);
                }
            }
        }
    }
