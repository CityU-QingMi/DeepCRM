	private DeclareParentsAdvisor(Class<?> interfaceType, String typePattern, Class<?> implementationClass, Advice advice) {
		this.introducedInterface = interfaceType;
		ClassFilter typePatternFilter = new TypePatternClassFilter(typePattern);

		// Excludes methods implemented.
		ClassFilter exclusion = clazz -> !(introducedInterface.isAssignableFrom(clazz));

		this.typePatternClassFilter = ClassFilters.intersection(typePatternFilter, exclusion);
		this.advice = advice;
	}
