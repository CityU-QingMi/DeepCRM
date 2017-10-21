	private static List<Method> getDefaultMethods(Class<?> clazz) {
		// @formatter:off
		// Visible default methods are interface default methods that have not
		// been overridden.
		List<Method> visibleDefaultMethods = Arrays.stream(clazz.getMethods())
				.filter(Method::isDefault)
				.collect(toCollection(ArrayList::new));
		if (visibleDefaultMethods.isEmpty()) {
			return visibleDefaultMethods;
		}
		return Arrays.stream(clazz.getInterfaces())
				.map(ReflectionUtils::getMethods)
				.flatMap(List::stream)
				.filter(visibleDefaultMethods::contains)
				.collect(toCollection(ArrayList::new));
		// @formatter:on
	}
