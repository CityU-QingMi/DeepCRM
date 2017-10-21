    public void testActionLevelMultipleResultNamesAnnotation() throws Exception {
        ServletContext context = EasyMock.createStrictMock(ServletContext.class);

        // Setup some mock jsps
        Set<String> resources = new HashSet<>();
        EasyMock.expect(context.getResourcePaths("/WEB-INF/location/namespace/")).andReturn(resources);
        EasyMock.replay(context);

        PackageConfig packageConfig = createPackageConfigBuilder("/namespace");

        this.conventionsService = new ConventionsServiceImpl("/WEB-INF/location");
        DefaultResultMapBuilder builder = new DefaultResultMapBuilder(context, container, "dispatcher,velocity,freemarker");
        Map<String, ResultConfig> results = builder.build(ActionLevelResultsNamesAction.class, getAnnotation(ActionLevelResultsNamesAction.class, "execute", Action.class), "action-level-results", packageConfig);
        assertEquals(4, results.size());
        assertEquals("error", results.get("error").getName());
        assertEquals("input", results.get("input").getName());
        assertEquals("success", results.get("success").getName());
        assertEquals("failure", results.get("failure").getName());
        assertEquals(3, results.get("error").getParams().size());
        assertEquals("/WEB-INF/location/namespace/error.jsp", results.get("error").getParams().get("location"));
        assertEquals("org.apache.struts2.result.ServletDispatcherResult", results.get("error").getClassName());
        assertEquals("value", results.get("success").getParams().get("key"));
        assertEquals("value1", results.get("success").getParams().get("key1"));
        assertEquals(3, results.get("input").getParams().size());
        assertEquals("/WEB-INF/location/namespace/error.jsp", results.get("input").getParams().get("location"));
        assertEquals("org.apache.struts2.result.ServletDispatcherResult", results.get("input").getClassName());
        assertEquals(3, results.get("failure").getParams().size());
        assertEquals("/WEB-INF/location/namespace/action-failure.jsp", results.get("failure").getParams().get("location"));
        assertEquals("org.apache.struts2.result.ServletDispatcherResult", results.get("failure").getClassName());
        assertEquals(3, results.get("success").getParams().size());
        assertEquals("/WEB-INF/location/namespace/action-success.jsp", results.get("success").getParams().get("location"));
        assertEquals("org.apache.struts2.result.ServletDispatcherResult", results.get("success").getClassName());
        EasyMock.verify(context);
    }
