    public boolean isFirstCustomization() {
        try {
            return (WebloggerFactory
                    .getWeblogger()
                    .getWeblogManager()
                    .getTemplateByAction(getActionWeblog(),
                            ComponentType.WEBLOG) == null);
        } catch (WebloggerException ex) {
            log.error("Error looking up weblog template", ex);
        }
        return false;
    }
