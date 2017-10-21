        public void loadPackages() throws ConfigurationException {
            PackageConfig packageConfig = new PackageConfig.Builder("default")
                    .addActionConfig(ANNOTATED_ACTION, new ActionConfig.Builder("defaultPackage", ANNOTATED_ACTION, AnnotatedAction.class.getName())
                            .addInterceptors(Collections.singletonList(new InterceptorMapping("annotationWorkflow", annotationWorkflow)))
                            .addResultConfig(new ResultConfig.Builder("success", MockResult.class.getName()).build())
                            .build())
                    .addActionConfig(SHORTCIRCUITED_ACTION, new ActionConfig.Builder("defaultPackage", SHORTCIRCUITED_ACTION, ShortcircuitedAction.class.getName())
                            .addInterceptors(Collections.singletonList(new InterceptorMapping("annotationWorkflow", annotationWorkflow)))
                            .addResultConfig(new ResultConfig.Builder("shortcircuit", MockResult.class.getName()).build())
                            .build())
                    .build();
            config.addPackageConfig("defaultPackage", packageConfig);
            config.addPackageConfig("default", new PackageConfig.Builder(packageConfig).name("default").build());
        }
