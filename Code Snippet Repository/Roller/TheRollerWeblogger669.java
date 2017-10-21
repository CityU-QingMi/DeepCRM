    public void myValidate()  {
        
        String allowed = WebloggerConfig.getProperty("username.allowedChars");
        if(allowed == null || allowed.trim().length() == 0) {
            allowed = Register.DEFAULT_ALLOWED_CHARS;
        }
        
        // make sure handle only contains safe characters
        String safe = CharSetUtils.keep(getBean().getHandle(), allowed);
        if (!safe.equals(getBean().getHandle()) ) {
            addError("createWeblog.error.invalidHandle");
        }
        
        // make sure theme was specified and is a valid value
        
        // make sure handle isn't already taken
        if(!StringUtils.isEmpty(getBean().getHandle())) {
            try {
                if (WebloggerFactory.getWeblogger().getWeblogManager()
                        .getWeblogByHandle(getBean().getHandle()) != null) {
                    addError("createWeblog.error.handleExists");
                    // reset handle
                    getBean().setHandle(null);
                }
            } catch (WebloggerException ex) {
                log.error("error checking for weblog", ex);
                addError("Unexpected error validating weblog -- check Roller logs");
            }
        }
    }
