        public void loadPackages() {
            
            PackageConfig packageContext = new PackageConfig.Builder("nestedActionTest")
                .addActionConfig(SIMPLE_ACTION_NAME, new ActionConfig.Builder("nestedActionTest", SIMPLE_ACTION_NAME, SimpleAction.class.getName())
                        .addResultConfig(new ResultConfig.Builder(Action.SUCCESS, MockResult.class.getName()).build())
                        .addResultConfig(new ResultConfig.Builder(Action.ERROR, MockResult.class.getName()).build())
                        .build())
                .addActionConfig(NO_STACK_ACTION_NAME, new ActionConfig.Builder("nestedActionTest", NO_STACK_ACTION_NAME, NestedAction.class.getName())
                        .addResultConfig(new ResultConfig.Builder(Action.SUCCESS, MockResult.class.getName()).build())
                        .methodName("noStack")
                        .build())
                .addActionConfig(STACK_ACTION_NAME, new ActionConfig.Builder("nestedActionTest", STACK_ACTION_NAME, NestedAction.class.getName())
                        .addResultConfig(new ResultConfig.Builder(Action.SUCCESS, MockResult.class.getName()).build())
                        .methodName("stack")
                        .build())
                .namespace(NAMESPACE)
                .build();
            configuration.addPackageConfig("nestedActionTest", packageContext);
        }
