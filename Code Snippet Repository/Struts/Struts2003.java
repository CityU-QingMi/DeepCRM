    public void testIfStrutsEncodingIsSetProperty() throws Exception {
        FreemarkerManager mgr = new FreemarkerManager();
        mgr.setEncoding("UTF-8");
        DefaultFileManagerFactory factory = new DefaultFileManagerFactory();
        container.inject(factory);
        mgr.setFileManagerFactory(factory);
        mgr.setThemeTemplateLoader(new FreemarkerThemeTemplateLoader());
        StrutsMockServletContext servletContext = new StrutsMockServletContext();
        servletContext.setAttribute(FreemarkerManager.CONFIG_SERVLET_CONTEXT_KEY, null);
        freemarker.template.Configuration conf = mgr.getConfiguration(servletContext);
        assertEquals(conf.getDefaultEncoding(), "UTF-8");
    }
