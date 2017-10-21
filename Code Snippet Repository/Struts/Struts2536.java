    public void testResultPath() throws Exception {
        ServletContext context = mockServletContext("/class-level");

        // Test with a result path
        PackageConfig packageConfig = createPackageConfigBuilder("/namespace");
        this.conventionsService = new ConventionsServiceImpl("/not-used");
        DefaultResultMapBuilder builder = new DefaultResultMapBuilder(context, container, "dispatcher,velocity,freemarker");
        Map<String, ResultConfig> results = builder.build(ClassLevelResultPathAction.class, null, "action", packageConfig);
        verify(context, "/class-level", results, false);
    }
