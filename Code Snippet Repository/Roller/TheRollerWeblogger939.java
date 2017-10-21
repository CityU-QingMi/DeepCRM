    @Override
    public void tearDown() throws Exception {
        PropertiesManager pmgr = WebloggerFactory.getWeblogger().getPropertiesManager();
        Map config = pmgr.getProperties();
        ((RuntimeConfigProperty) config.get("uploads.dir.maxsize")).setValue("30000");
        ((RuntimeConfigProperty) config.get("uploads.types.forbid")).setValue("");
        ((RuntimeConfigProperty) config.get("uploads.types.allowed")).setValue("");
        ((RuntimeConfigProperty) config.get("uploads.enabled")).setValue("true");
        pmgr.saveProperties(config);
        TestUtils.endSession(true);
    }
