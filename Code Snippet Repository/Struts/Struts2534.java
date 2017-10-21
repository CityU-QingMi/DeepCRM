    public void testGlobalResultOverride() throws Exception {

        ServletContext context = EasyMock.createStrictMock(ServletContext.class);
        String resultPath = "/WEB-INF/location";
        // Setup some mock jsps
        Set<String> resources = new HashSet<>();
        resources.add(resultPath + "/namespace/action.jsp");
        resources.add(resultPath + "/namespace/action-success.jsp");
        resources.add(resultPath + "/namespace/action-error.jsp");
        EasyMock.expect(context.getResourcePaths(resultPath + "/namespace/")).andReturn(resources);
        EasyMock.replay(context);

        this.conventionsService = new ConventionsServiceImpl("/WEB-INF/location");
        DefaultResultMapBuilder builder = new DefaultResultMapBuilder(context, container, "dispatcher,velocity,freemarker");

        ResultTypeConfig resultType = new ResultTypeConfig.Builder("dispatcher", ServletDispatcherResult.class.getName()).
                addParam("key", "value").addParam("key1", "value1").defaultResultParam("location").build();
        ResultConfig globalError = new ResultConfig.Builder("error", ServletDispatcherResult.class.getName()).
                addParam("location", "/globalError.jsp").
                build();
        PackageConfig packageConfig = new PackageConfig.Builder("package").
                namespace("/namespace").
                defaultResultType("dispatcher").
                addResultTypeConfig(resultType).
                addGlobalResultConfig(globalError).
                build();


        Map<String, ResultConfig> results = builder.build(GlobalResultOverrideAction.class, null, "action", packageConfig);
        ResultConfig result = results.get("error");
        assertNotNull(result);
        assertEquals(resultPath + "/namespace/action-error.jsp", result.getParams().get("location"));
    }
