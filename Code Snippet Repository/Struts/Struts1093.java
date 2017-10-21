    private Map<String,ActionConfig> buildActionConfigMap() {
        Map<String, ActionConfig> map = new HashMap<>();

        HashMap<String, String> params = new HashMap<>();
        params.put("first", "{1}");
        params.put("second", "{2}");

        ActionConfig config = new ActionConfig.Builder("package-{1}", "foo/*/*", "foo.bar.{1}Action")
                .methodName("do{2}")
                .addParams(params)
                .addExceptionMapping(new ExceptionMappingConfig.Builder("foo{1}", "java.lang.{2}Exception", "success{1}")
                        .addParams(new HashMap<>(params))
                    .build())
                .addInterceptor(new InterceptorMapping(null, null))
                .addResultConfig(new ResultConfig.Builder("success{1}", "foo.{2}").addParams(params).build())
                .setStrictMethodInvocation(false)
                .build();
        map.put("foo/*/*", config);
        
        config = new ActionConfig.Builder("package-{1}", "bar/*/**", "bar")
                .methodName("do{1}_{1}")
                .addParam("first", "{2}")
                .setStrictMethodInvocation(false)
                .build();
        
        map.put("bar/*/**", config);

        config = new ActionConfig.Builder("package", "eventAdd!*", "bar")
                .methodName("{1}")
                .setStrictMethodInvocation(false)
                .build();

        map.put("addEvent!*", config);

        map.put("noWildcard", new ActionConfig.Builder("", "", "").build());

        return map;
    }
