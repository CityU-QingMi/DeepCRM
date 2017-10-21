    public void testGlobalResult() throws Exception {

        ServletContext context = mockServletContext("/WEB-INF/location");
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


        Map<String, ResultConfig> results = builder.build(GlobalResultAction.class, null, "action", packageConfig);
        ResultConfig result = results.get("error");
        assertNotNull(result);
        assertEquals("/globalError.jsp", result.getParams().get("location"));
    }
