	public static String determineBeanNameFor(Method beanMethod) {
		// By default, the bean name is the name of the @Bean-annotated method
		String beanName = beanMethod.getName();

		// Check to see if the user has explicitly set a custom bean name...
		Bean bean = AnnotatedElementUtils.findMergedAnnotation(beanMethod, Bean.class);
		if (bean != null && bean.name().length > 0) {
			beanName = bean.name()[0];
		}

		return beanName;
	}
