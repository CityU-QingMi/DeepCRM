    @Override
    protected void setUp() throws Exception {
        super.setUp();

        this.contentLoader = new DefaultStaticContentLoader();
        MockServletContext servletContext = new MockServletContext();
        req = new MockHttpServletRequest(servletContext);
        res = new MockHttpServletResponse();


        Mock hostConfigMock = new Mock(HostConfig.class);
        hostConfigMock.expectAndReturn("getInitParameter", C.args(C.eq("packages")), null);
        hostConfigMock.expectAndReturn("getInitParameter", C.args(C.eq("loggerFactory")), null);

        contentLoader.setEncoding("utf-8");

        contentLoader.setHostConfig((HostConfig) hostConfigMock.proxy());
    }
