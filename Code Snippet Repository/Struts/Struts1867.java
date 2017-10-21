        public void loadPackages() throws ConfigurationException {


            // interceptors
            waitInterceptor = new ExecuteAndWaitInterceptor();
            parametersInterceptor = new ParametersInterceptor();
            PackageConfig wait = new PackageConfig.Builder("")
                .addActionConfig("action1", new ActionConfig.Builder("", "action1", ExecuteAndWaitDelayAction.class.getName())
                    .addResultConfig(new ResultConfig.Builder(Action.SUCCESS, MockResult.class.getName()).build())
                    .addResultConfig(new ResultConfig.Builder(ExecuteAndWaitInterceptor.WAIT, MockResult.class.getName()).build())
                    .addInterceptor(new InterceptorMapping("params", parametersInterceptor))
                    .addInterceptor(new InterceptorMapping("execAndWait", waitInterceptor))
                .build())
            .build();
            configuration.addPackageConfig("", wait);
        }
