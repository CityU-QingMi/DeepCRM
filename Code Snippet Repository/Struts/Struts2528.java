    public void testClassPath() throws Exception {
        ServletContext context = EasyMock.createNiceMock(ServletContext.class);

        ResultTypeConfig resultType = new ResultTypeConfig.Builder("freemarker", "org.apache.struts2.result.ServletDispatcherResult").
                defaultResultParam("location").build();
        PackageConfig packageConfig = new PackageConfig.Builder("package").
                defaultResultType("dispatcher").addResultTypeConfig(resultType).build();

        this.conventionsService = new ConventionsServiceImpl("/WEB-INF/component");
        DefaultResultMapBuilder builder = new DefaultResultMapBuilder(context, container, "dispatcher,velocity,freemarker");
        Map<String, ResultConfig> results = builder.build(NoAnnotationAction.class, null, "no-annotation", packageConfig);
        assertEquals(4, results.size());
        assertEquals("input", results.get("input").getName());
        assertEquals("error", results.get("error").getName());
        assertEquals("success", results.get("success").getName());
        assertEquals("foo", results.get("foo").getName());
        assertEquals(1, results.get("success").getParams().size());
        assertEquals("/WEB-INF/component/no-annotation.ftl", results.get("success").getParams().get("location"));
        assertEquals(1, results.get("input").getParams().size());
        assertEquals("/WEB-INF/component/no-annotation.ftl", results.get("input").getParams().get("location"));
        assertEquals(1, results.get("error").getParams().size());
        assertEquals("/WEB-INF/component/no-annotation.ftl", results.get("error").getParams().get("location"));
        assertEquals(1, results.get("foo").getParams().size());
        assertEquals("/WEB-INF/component/no-annotation-foo.ftl", results.get("foo").getParams().get("location"));
    }
