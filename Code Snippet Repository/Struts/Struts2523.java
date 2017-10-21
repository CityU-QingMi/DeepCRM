    public void testActionLevelSingleResultAnnotation() throws Exception {
        ServletContext context = EasyMock.createStrictMock(ServletContext.class);

        // Setup some mock jsps
        Set<String> resources = new HashSet<>();
        EasyMock.expect(context.getResourcePaths("/WEB-INF/location/namespace/")).andReturn(resources);
        EasyMock.replay(context);

        PackageConfig packageConfig = createPackageConfigBuilder("/namespace");

        this.conventionsService = new ConventionsServiceImpl("/WEB-INF/location");
        DefaultResultMapBuilder builder = new DefaultResultMapBuilder(context, container, "dispatcher,velocity,freemarker");
        Map<String, ResultConfig> results = builder.build(ActionLevelResultAction.class, getAnnotation(ActionLevelResultAction.class, "execute", Action.class), "action-level-result", packageConfig);
        assertEquals(1, results.size());
        assertEquals("success", results.get("success").getName());
        assertEquals(3, results.get("success").getParams().size());
        assertEquals("org.apache.struts2.result.ServletDispatcherResult", results.get("success").getClassName());
        assertEquals("/WEB-INF/location/namespace/action-success.jsp", results.get("success").getParams().get("location"));
        assertEquals("value", results.get("success").getParams().get("key"));
        assertEquals("value1", results.get("success").getParams().get("key1"));
        EasyMock.verify(context);
    }
