    public String bootstrap() {
        log.info("ENTERING");

        if (WebloggerFactory.isBootstrapped()) {
            log.info("EXITING - already bootstrapped, forwarding to Roller");
            return SUCCESS;
        }

        try {
            // trigger bootstrapping process
            WebloggerFactory.bootstrap();

            // trigger initialization process
            WebloggerFactory.getWeblogger().initialize();

            // also need to do planet if it's configured
            if (WebloggerConfig.getBooleanProperty("planet.aggregator.enabled")) {

                // Now prepare the core services of planet so we can bootstrap it
                try {
                    WebloggerStartup.prepare();
                } catch (Exception ex) {
                    log.fatal("Roller Planet startup failed during app preparation", ex);
                }
            }
            log.info("EXITING - Bootstrap successful, forwarding to Roller");
            return SUCCESS;

        } catch (BootstrapException ex) {
            log.error("BootstrapException", ex);
            rootCauseException = ex;
        } catch (WebloggerException ex) {
            log.error("WebloggerException", ex);
            rootCauseException = ex;
        } catch (Exception e) {
            log.error("Exception", e);
            rootCauseException = e;
        }

        log.info("EXITING - Bootstrap failed, forwarding to error page");
        setPageTitle("installer.error.unknown.pageTitle");
        return BOOTSTRAP;
    }
