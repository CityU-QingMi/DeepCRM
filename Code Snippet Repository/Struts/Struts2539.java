    public void testFromServletContextNotFlat() throws Exception {
        ServletContext context = EasyMock.createStrictMock(ServletContext.class);

        // Setup some mock jsps
        Set<String> resources = new HashSet<>();
        resources.add("/WEB-INF/location/namespace/no-annotation/index.ftl");
        resources.add("/WEB-INF/location/namespace/no-annotation/success.jsp");
        resources.add("/WEB-INF/location/namespace/no-annotation/failure.jsp");
        EasyMock.expect(context.getResourcePaths("/WEB-INF/location/namespace/no-annotation")).andReturn(resources);
        EasyMock.replay(context);

        PackageConfig packageConfig = createPackageConfigBuilder("/namespace");

        this.conventionsService = new ConventionsServiceImpl("/WEB-INF/location");
        DefaultResultMapBuilder builder = new DefaultResultMapBuilder(context, container, "dispatcher,velocity,freemarker");
        builder.setFlatResultLayout("false");

        Map<String, ResultConfig> results = builder.build(NoAnnotationAction.class, null, "no-annotation", packageConfig);

        assertEquals(3, results.size());

        assertEquals("success", results.get("success").getName());
        assertEquals(3, results.get("success").getParams().size());
        assertEquals("org.apache.struts2.result.ServletDispatcherResult", results.get("success").getClassName());
        assertEquals("/WEB-INF/location/namespace/no-annotation/success.jsp", results.get("success").getParams().get("location"));

        assertEquals(1, results.get("index").getParams().size());
        assertEquals("org.apache.struts2.views.freemarker.FreemarkerResult", results.get("index").getClassName());
        assertEquals("/WEB-INF/location/namespace/no-annotation/index.ftl", results.get("index").getParams().get("location"));

        assertEquals(3, results.get("failure").getParams().size());
        assertEquals("org.apache.struts2.result.ServletDispatcherResult", results.get("success").getClassName());
        assertEquals("/WEB-INF/location/namespace/no-annotation/failure.jsp", results.get("failure").getParams().get("location"));
        EasyMock.verify(context);
    }
