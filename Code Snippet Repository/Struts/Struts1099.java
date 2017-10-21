    public void testMultipleSameInterceptors() throws Exception {
        InterceptorConfig interceptorConfig1 = new InterceptorConfig.Builder("interceptor1", "com.opensymphony.xwork2.config.providers.InterceptorBuilderTest$MockInterceptor1").build();
        InterceptorConfig interceptorConfig2 = new InterceptorConfig.Builder("interceptor2", "com.opensymphony.xwork2.config.providers.InterceptorBuilderTest$MockInterceptor2").build();

        InterceptorStackConfig interceptorStackConfig1 = new InterceptorStackConfig.Builder("multiStack")
                .addInterceptor(new InterceptorMapping(interceptorConfig1.getName(), objectFactory.buildInterceptor(interceptorConfig1, Collections.<String, String>emptyMap())))
                .addInterceptor(new InterceptorMapping(interceptorConfig2.getName(), objectFactory.buildInterceptor(interceptorConfig2, Collections.<String, String>emptyMap())))
                .addInterceptor(new InterceptorMapping(interceptorConfig1.getName(), objectFactory.buildInterceptor(interceptorConfig1, Collections.<String, String>emptyMap())))
                .build();

        PackageConfig packageConfig = new PackageConfig.Builder("package1")
                .namespace("/namespace")
                .addInterceptorConfig(interceptorConfig1)
                .addInterceptorConfig(interceptorConfig2)
                .addInterceptorConfig(interceptorConfig1)
                .addInterceptorStackConfig(interceptorStackConfig1)
                .build();

        List interceptorMappings =  InterceptorBuilder.constructInterceptorReference(packageConfig, "multiStack",
                        new LinkedHashMap<String, String>() {
                            {
                                put("interceptor1.param1", "interceptor1_value1");
                                put("interceptor1.param2", "interceptor1_value2");
                            }
                        }, null,  objectFactory);

        assertEquals(interceptorMappings.size(), 3);

        assertEquals(((InterceptorMapping) interceptorMappings.get(0)).getName(), "interceptor1");
        assertNotNull(((InterceptorMapping) interceptorMappings.get(0)).getInterceptor());
        assertEquals(((InterceptorMapping) interceptorMappings.get(0)).getInterceptor().getClass(), MockInterceptor1.class);
        assertEquals(((MockInterceptor1) ((InterceptorMapping) interceptorMappings.get(0)).getInterceptor()).getParam1(), "interceptor1_value1");
        assertEquals(((MockInterceptor1) ((InterceptorMapping) interceptorMappings.get(0)).getInterceptor()).getParam2(), "interceptor1_value2");

        assertEquals(((InterceptorMapping) interceptorMappings.get(1)).getName(), "interceptor2");
        assertNotNull(((InterceptorMapping) interceptorMappings.get(1)).getInterceptor());
        assertEquals(((InterceptorMapping) interceptorMappings.get(1)).getInterceptor().getClass(), MockInterceptor2.class);

        assertEquals(((InterceptorMapping) interceptorMappings.get(2)).getName(), "interceptor1");
        assertNotNull(((InterceptorMapping) interceptorMappings.get(2)).getInterceptor());
        assertEquals(((InterceptorMapping) interceptorMappings.get(2)).getInterceptor().getClass(), MockInterceptor1.class);
        assertEquals(((MockInterceptor1) ((InterceptorMapping) interceptorMappings.get(2)).getInterceptor()).getParam1(), "interceptor1_value1");
        assertEquals(((MockInterceptor1) ((InterceptorMapping) interceptorMappings.get(2)).getInterceptor()).getParam2(), "interceptor1_value2");
    }
