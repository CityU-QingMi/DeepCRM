	private static Set<String> initBasePackages(ControllerAdvice annotation) {
		Set<String> basePackages = new LinkedHashSet<>();
		for (String basePackage : annotation.basePackages()) {
			if (StringUtils.hasText(basePackage)) {
				basePackages.add(adaptBasePackage(basePackage));
			}
		}
		for (Class<?> markerClass : annotation.basePackageClasses()) {
			basePackages.add(adaptBasePackage(ClassUtils.getPackageName(markerClass)));
		}
		return basePackages;
	}
