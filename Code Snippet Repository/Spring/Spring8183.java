	@SuppressWarnings("")
	private void invokeApplicationContextInitializers(ConfigurableApplicationContext context,
			MergedContextConfiguration mergedConfig) {

		Set<Class<? extends ApplicationContextInitializer<?>>> initializerClasses =
				mergedConfig.getContextInitializerClasses();
		if (initializerClasses.isEmpty()) {
			// no ApplicationContextInitializers have been declared -> nothing to do
			return;
		}

		List<ApplicationContextInitializer<ConfigurableApplicationContext>> initializerInstances = new ArrayList<>();
		Class<?> contextClass = context.getClass();

		for (Class<? extends ApplicationContextInitializer<?>> initializerClass : initializerClasses) {
			Class<?> initializerContextClass =
					GenericTypeResolver.resolveTypeArgument(initializerClass, ApplicationContextInitializer.class);
			if (initializerContextClass != null && !initializerContextClass.isInstance(context)) {
				throw new ApplicationContextException(String.format(
						"Could not apply context initializer [%s] since its generic parameter [%s] " +
						"is not assignable from the type of application context used by this " +
						"context loader: [%s]", initializerClass.getName(), initializerContextClass.getName(),
						contextClass.getName()));
			}
			initializerInstances.add((ApplicationContextInitializer<ConfigurableApplicationContext>) BeanUtils.instantiateClass(initializerClass));
		}

		AnnotationAwareOrderComparator.sort(initializerInstances);
		for (ApplicationContextInitializer<ConfigurableApplicationContext> initializer : initializerInstances) {
			initializer.initialize(context);
		}
	}
