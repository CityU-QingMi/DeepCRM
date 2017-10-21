    public static void bootstrap() throws BootstrapException {
        
        // if the app hasn't been properly started so far then bail
        if (!WebloggerStartup.isPrepared()) {
            throw new IllegalStateException("Cannot bootstrap until application has been properly prepared");
        }
        
        // lookup our default provider and instantiate it
        WebloggerProvider defaultProvider;
        String providerClassname = WebloggerConfig.getProperty("weblogger.provider.class");
        if(providerClassname != null) {
            try {
                Class providerClass = Class.forName(providerClassname);
                defaultProvider = (WebloggerProvider) providerClass.newInstance();
            } catch (Exception ex) {
                throw new BootstrapException("Error instantiating default provider: " + providerClassname + "; exception message: " + ex.getMessage(), ex);
            }
        } else {
            throw new NullPointerException("No provider specified in config property 'weblogger.provider.class'");
        }

        // now just bootstrap using our default provider
        bootstrap(defaultProvider);
    }
