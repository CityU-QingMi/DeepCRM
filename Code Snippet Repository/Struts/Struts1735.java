    public void testSettings() {
        Settings settings = new DefaultSettings();

        assertEquals("12345", settings.get(StrutsConstants.STRUTS_MULTIPART_MAXSIZE));
        assertEquals("\temp", settings.get(StrutsConstants.STRUTS_MULTIPART_SAVEDIR));

        assertEquals("test,org/apache/struts2/othertest", settings.get( StrutsConstants.STRUTS_CUSTOM_PROPERTIES));
        assertEquals("testvalue", settings.get("testkey"));
        assertEquals("othertestvalue", settings.get("othertestkey"));

        int count = getKeyCount(settings);
        assertEquals(12, count);
    }
