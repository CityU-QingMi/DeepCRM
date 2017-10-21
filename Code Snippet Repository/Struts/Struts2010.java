    @Override
    public void setUp() throws Exception {
        super.setUp();

        servletContext = EasyMock.createNiceMock(ServletContext.class);
        EasyMock.expect(servletContext.getInitParameter("TemplatePath")).andReturn(null);
        EasyMock.expect(servletContext.getInitParameter("templatePath")).andReturn(null);

        EasyMock.expect(servletContext.getAttribute(FreemarkerManager.CONFIG_SERVLET_CONTEXT_KEY)).andReturn(freemarkerConfig).anyTimes();
    }
