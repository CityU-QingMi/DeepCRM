    public void testBuild() throws Exception {
        ServletContext context = mockServletContext("/WEB-INF/location");

        // Test with a slash
        PackageConfig packageConfig = createPackageConfigBuilder("/namespace");
        this.conventionsService = new ConventionsServiceImpl("/WEB-INF/location");
        DefaultResultMapBuilder builder = new DefaultResultMapBuilder(context, container, "dispatcher,velocity,freemarker");
        Map<String, ResultConfig> results = builder.build(NoAnnotationAction.class, null, "action", packageConfig);
        verify(context, "/WEB-INF/location", results, false);

        // Test without a slash
        context = mockServletContext("/WEB-INF/location");
        packageConfig = createPackageConfigBuilder("namespace");
        this.conventionsService = new ConventionsServiceImpl("/WEB-INF/location");
        builder = new DefaultResultMapBuilder(context, container, "dispatcher,velocity,freemarker");
        results = builder.build(NoAnnotationAction.class, null, "action", packageConfig);
        verify(context, "/WEB-INF/location", results, false);
    }
