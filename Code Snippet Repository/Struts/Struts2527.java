    public void testResultOverrride() throws Exception {
        ServletContext context = mockServletContext("/WEB-INF/location");

        // Test with a slash
        PackageConfig packageConfig = createPackageConfigBuilder("/namespace");
        this.conventionsService = new ConventionsServiceImpl("/WEB-INF/location");
        DefaultResultMapBuilder builder = new DefaultResultMapBuilder(context, container, "dispatcher,velocity,freemarker");
        Action actionAnn = OverrideResultAction.class.getMethod("execute").getAnnotation(Action.class);
        Map<String, ResultConfig> results = builder.build(OverrideResultAction.class, actionAnn, "action100", packageConfig);
        ResultConfig result = results.get("error");
        assertNotNull(result);
        assertEquals("/WEB-INF/location/namespace/error-overriden.jsp", result.getParams().get("location"));
    }
