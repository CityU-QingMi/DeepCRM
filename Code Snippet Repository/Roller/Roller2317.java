    public String save() {
        PropertiesManager mgr = WebloggerFactory.getWeblogger().getPropertiesManager();
        try {
            RuntimeConfigProperty frontpageBlogProp = mgr.getProperty("site.frontpage.weblog.handle");
            frontpageBlogProp.setValue(frontpageBlog);
            mgr.saveProperty(frontpageBlogProp);

            RuntimeConfigProperty aggregatedProp = mgr.getProperty("site.frontpage.weblog.aggregated");
            aggregatedProp.setValue(aggregated.toString());
            mgr.saveProperty(aggregatedProp);

            WebloggerFactory.getWeblogger().flush();

            addMessage("frontpageConfig.values.saved");

        } catch (WebloggerException ex) {
            LOG.error("ERROR saving frontpage configuration", ex);
            addError("frontpageConfig.values.error");
        }
        return "home";
    }
