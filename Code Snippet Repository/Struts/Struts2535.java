    public void testNull() throws Exception {
        ServletContext context = EasyMock.createStrictMock(ServletContext.class);
        EasyMock.expect(context.getResourcePaths("/WEB-INF/location/namespace/")).andReturn(null);
        EasyMock.replay(context);

        // Test with a slash
        PackageConfig packageConfig = createPackageConfigBuilder("/namespace");
        this.conventionsService = new ConventionsServiceImpl("/WEB-INF/location");
        DefaultResultMapBuilder builder = new DefaultResultMapBuilder(context, container, "dispatcher,velocity,freemarker");
        Map<String, ResultConfig> results = builder.build(NoAnnotationAction.class, null, "action", packageConfig);
        assertEquals(0, results.size());
        EasyMock.verify(context);
    }
