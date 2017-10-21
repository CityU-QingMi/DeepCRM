    public void testBuildInterceptor_1() throws Exception {
        InterceptorStackConfig interceptorStackConfig1 = new InterceptorStackConfig.Builder("interceptorStack1").build();

        InterceptorConfig interceptorConfig1 = new InterceptorConfig.Builder("interceptor1", "com.opensymphony.xwork2.config.providers.InterceptorBuilderTest$MockInterceptor1").build();

        InterceptorConfig interceptorConfig2 = new InterceptorConfig.Builder("interceptor2", "com.opensymphony.xwork2.config.providers.InterceptorBuilderTest$MockInterceptor2").build();


        PackageConfig packageConfig = new PackageConfig.Builder("package1").namespace("/namespace").addInterceptorConfig(interceptorConfig1).addInterceptorConfig(interceptorConfig2).addInterceptorStackConfig(interceptorStackConfig1).build();

        List
                interceptorMappings =
                InterceptorBuilder.constructInterceptorReference(packageConfig, "interceptorStack1",
                        new LinkedHashMap<String, String>() {
                            private static final long serialVersionUID = -1358620486812957895L;

                            {
                                put("interceptor1.param1", "interceptor1_value1");
                                put("interceptor1.param2", "interceptor1_value2");
                                put("interceptor2.param1", "interceptor2_value1");
                                put("interceptor2.param2", "interceptor2_value2");
                            }
                        },null,  objectFactory);

        assertEquals(interceptorMappings.size(), 2);

        assertEquals(((InterceptorMapping) interceptorMappings.get(0)).getName(), "interceptor1");
        assertNotNull(((InterceptorMapping) interceptorMappings.get(0)).getInterceptor());
        assertEquals(((InterceptorMapping) interceptorMappings.get(0)).getInterceptor().getClass(), MockInterceptor1.class);
        assertEquals(((MockInterceptor1) ((InterceptorMapping) interceptorMappings.get(0)).getInterceptor()).getParam1(), "interceptor1_value1");
        assertEquals(((MockInterceptor1) ((InterceptorMapping) interceptorMappings.get(0)).getInterceptor()).getParam2(), "interceptor1_value2");

        assertEquals(((InterceptorMapping) interceptorMappings.get(1)).getName(), "interceptor2");
        assertNotNull(((InterceptorMapping) interceptorMappings.get(1)).getInterceptor());
        assertEquals(((InterceptorMapping) interceptorMappings.get(1)).getInterceptor().getClass(), MockInterceptor2.class);
        assertEquals(((MockInterceptor2) ((InterceptorMapping) interceptorMappings.get(1)).getInterceptor()).getParam1(), "interceptor2_value1");
        assertEquals(((MockInterceptor2) ((InterceptorMapping) interceptorMappings.get(1)).getInterceptor()).getParam2(), "interceptor2_value2");
    }
