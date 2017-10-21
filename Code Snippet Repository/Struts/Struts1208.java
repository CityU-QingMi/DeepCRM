        public void loadPackages() throws ConfigurationException {
            HashMap<String, String> interceptorParams = new HashMap<>();
            interceptorParams.put("excludes", "blah,bar");

            HashMap successParams1 = new HashMap();
            successParams1.put("propertyName", "baz");
            successParams1.put("expectedValue", 1);

            HashMap successParams2 = new HashMap();
            successParams2.put("propertyName", "blah");
            successParams2.put("expectedValue", null);

            InterceptorConfig chainingInterceptorConfig =  new InterceptorConfig.Builder("chainStack", ChainingInterceptor.class.getName()).build();
            PackageConfig packageConfig = new PackageConfig.Builder("default")
                    .addActionConfig(CHAINED_ACTION, new ActionConfig.Builder("defaultPackage", CHAINED_ACTION, SimpleAction.class.getName())
                            .addResultConfig(new ResultConfig.Builder(Action.ERROR, ActionChainResult.class.getName()).addParam("actionName", CHAINTO_ACTION).build())
                            .build())
                    .addActionConfig(CHAINTO_ACTION, new ActionConfig.Builder("defaultPackage", CHAINTO_ACTION, SimpleAction.class.getName())
                            .addInterceptors(Collections.singletonList(new InterceptorMapping("chainStack", objectFactory.buildInterceptor(chainingInterceptorConfig, interceptorParams))))
                            .addResultConfig(new ResultConfig.Builder(Action.SUCCESS, TestResult.class.getName()).addParams(successParams1).build())
                            .addResultConfig(new ResultConfig.Builder(Action.ERROR, TestResult.class.getName()).addParams(successParams2).build())
                            .build())
                    .build();
            config.addPackageConfig("defaultPackage", packageConfig);
            config.addPackageConfig("default", new PackageConfig.Builder(packageConfig).name("default").build());
        }
