    public void testFromServletContext() throws Exception {
        ServletContext context = EasyMock.createStrictMock(ServletContext.class);

        // Setup some mock jsps
        Set<String> resources = new HashSet<>();
        resources.add("/WEB-INF/location/namespace/no-annotation.ftl");
        resources.add("/WEB-INF/location/namespace/no-annotation-success.jsp");
        resources.add("/WEB-INF/location/namespace/no-annotation-failure.jsp");
        EasyMock.expect(context.getResourcePaths("/WEB-INF/location/namespace/")).andReturn(resources);
        EasyMock.replay(context);

        PackageConfig packageConfig = createPackageConfigBuilder("/namespace");

        this.conventionsService = new ConventionsServiceImpl("/WEB-INF/location");
        DefaultResultMapBuilder builder = new DefaultResultMapBuilder(context, container, "dispatcher,velocity,freemarker");
        Map<String, ResultConfig> results = builder.build(NoAnnotationAction.class, null, "no-annotation", packageConfig);
        assertEquals(4, results.size());
        assertEquals("success", results.get("success").getName());
        assertEquals(3, results.get("success").getParams().size());
        assertEquals("org.apache.struts2.result.ServletDispatcherResult", results.get("success").getClassName());
        assertEquals("/WEB-INF/location/namespace/no-annotation-success.jsp", results.get("success").getParams().get("location"));
        assertEquals(1, results.get("input").getParams().size());
        assertEquals("org.apache.struts2.views.freemarker.FreemarkerResult", results.get("input").getClassName());
        assertEquals("/WEB-INF/location/namespace/no-annotation.ftl", results.get("input").getParams().get("location"));
        assertEquals(1, results.get("error").getParams().size());
        assertEquals("org.apache.struts2.views.freemarker.FreemarkerResult", results.get("error").getClassName());
        assertEquals("/WEB-INF/location/namespace/no-annotation.ftl", results.get("error").getParams().get("location"));
        assertEquals(3, results.get("failure").getParams().size());
        assertEquals("org.apache.struts2.result.ServletDispatcherResult", results.get("success").getClassName());
        assertEquals("/WEB-INF/location/namespace/no-annotation-failure.jsp", results.get("failure").getParams().get("location"));
        EasyMock.verify(context);

    }
