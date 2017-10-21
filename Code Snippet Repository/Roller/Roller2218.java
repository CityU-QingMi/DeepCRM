    public void init(ServletConfig servletConfig) throws ServletException {

        super.init(servletConfig);

        log.info("Initializing PageServlet");

        this.excludeOwnerPages = WebloggerConfig
                .getBooleanProperty("cache.excludeOwnerEditPages");

        // get a reference to the weblog page cache
        this.weblogPageCache = WeblogPageCache.getInstance();

        // get a reference to the site wide cache
        this.siteWideCache = SiteWideCache.getInstance();

        // see if built-in referrer spam check is enabled
        this.processReferrers = WebloggerConfig
                .getBooleanProperty("site.blacklist.enable.referrers");

        log.info("Referrer spam check enabled = " + this.processReferrers);

        // check for possible robot pattern
        String robotPatternStr = WebloggerConfig
                .getProperty("referrer.robotCheck.userAgentPattern");
        if (robotPatternStr != null && robotPatternStr.length() > 0) {
            // Parse the pattern, and store the compiled form.
            try {
                robotPattern = Pattern.compile(robotPatternStr);
            } catch (Exception e) {
                // Most likely a PatternSyntaxException; log and continue as if
                // it is not set.
                log.error(
                        "Error parsing referrer.robotCheck.userAgentPattern value '"
                                + robotPatternStr
                                + "'.  Robots will not be filtered. ", e);
            }
        }

        // Development theme reloading
        themeReload = WebloggerConfig.getBooleanProperty("themes.reload.mode");
    }
