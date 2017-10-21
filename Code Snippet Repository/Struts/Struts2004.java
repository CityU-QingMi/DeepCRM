    public void testTemplateLoaderBaseOnFile() throws Exception {
        // given
        DummyFreemarkerManager manager = new DummyFreemarkerManager();
        StrutsMockServletContext servletContext = new StrutsMockServletContext();
        servletContext.setAttribute(FreemarkerManager.CONFIG_SERVLET_CONTEXT_KEY, null);

        String tmpPath = "file://" + FileUtils.getTempDirectoryPath();
        
        // when
        manager.load(servletContext, tmpPath);

        // then
        assertTrue(true); // should pass
    }
