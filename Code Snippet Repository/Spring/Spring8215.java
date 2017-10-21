	private static String[] mergeLocations(List<TestPropertySourceAttributes> attributesList) {
		List<String> locations = new ArrayList<>();
		for (TestPropertySourceAttributes attrs : attributesList) {
			if (logger.isTraceEnabled()) {
				logger.trace(String.format("Processing locations for TestPropertySource attributes %s", attrs));
			}
			String[] locationsArray = TestContextResourceUtils.convertToClasspathResourcePaths(
					attrs.getDeclaringClass(), attrs.getLocations());
			locations.addAll(0, Arrays.asList(locationsArray));
			if (!attrs.isInheritLocations()) {
				break;
			}
		}
		return StringUtils.toStringArray(locations);
	}
