	private ApplicationContext createContext(final ScopedProxyMode scopedProxyMode) {
		GenericWebApplicationContext context = new GenericWebApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.setIncludeAnnotationConfig(false);
		scanner.setScopeMetadataResolver(new ScopeMetadataResolver() {
			@Override
			public ScopeMetadata resolveScopeMetadata(BeanDefinition definition) {
				ScopeMetadata metadata = new ScopeMetadata();
				if (definition instanceof AnnotatedBeanDefinition) {
					AnnotatedBeanDefinition annDef = (AnnotatedBeanDefinition) definition;
					for (String type : annDef.getMetadata().getAnnotationTypes()) {
						if (type.equals(javax.inject.Singleton.class.getName())) {
							metadata.setScopeName(BeanDefinition.SCOPE_SINGLETON);
							break;
						}
						else if (annDef.getMetadata().getMetaAnnotationTypes(type).contains(javax.inject.Scope.class.getName())) {
							metadata.setScopeName(type.substring(type.length() - 13, type.length() - 6).toLowerCase());
							metadata.setScopedProxyMode(scopedProxyMode);
							break;
						}
						else if (type.startsWith("javax.inject")) {
							metadata.setScopeName(BeanDefinition.SCOPE_PROTOTYPE);
						}
					}
				}
				return metadata;
			}
		});

		// Scan twice in order to find errors in the bean definition compatibility check.
		scanner.scan(getClass().getPackage().getName());
		scanner.scan(getClass().getPackage().getName());

		context.registerAlias("classPathBeanDefinitionScannerJsr330ScopeIntegrationTests.SessionScopedTestBean", "session");
		context.refresh();
		return context;
	}
