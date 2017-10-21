     public void testClassLevelOverwriteInheritedSingleResultAnnotation() throws Exception {
        ServletContext context = EasyMock.createStrictMock(ServletContext.class);

        // Setup some mock jsps
        Set<String> resources = new HashSet<>();
        EasyMock.expect(context.getResourcePaths("/WEB-INF/location/namespace/")).andReturn(resources);
        EasyMock.replay(context);

        PackageConfig packageConfig = createPackageConfigBuilder("/namespace");

        this.conventionsService = new ConventionsServiceImpl("/WEB-INF/location");
        DefaultResultMapBuilder builder = new DefaultResultMapBuilder(context, container, "dispatcher,velocity,freemarker");
        Map<String, ResultConfig> results = builder.build(OverrideInheritedResultExtends.class, null, "result-inheritance-extends", packageConfig);
        assertEquals(1, results.size());
        assertEquals("error", results.get("error").getName());
        assertEquals(5, results.get("error").getParams().size());
        assertEquals("org.apache.struts2.result.ServletDispatcherResult", results.get("error").getClassName());
        assertEquals("/WEB-INF/location/namespace/error.jsp", results.get("error").getParams().get("location"));
        assertEquals("value", results.get("error").getParams().get("key"));
        assertEquals("value1", results.get("error").getParams().get("key1"));
        assertEquals("value-overwritten", results.get("error").getParams().get("key-overwritten"));
        assertEquals("value1-overwritten", results.get("error").getParams().get("key1-overwritten"));
        EasyMock.verify(context);
    }
