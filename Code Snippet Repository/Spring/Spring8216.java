	private static String[] mergeProperties(List<TestPropertySourceAttributes> attributesList) {
		List<String> properties = new ArrayList<>();
		for (TestPropertySourceAttributes attrs : attributesList) {
			if (logger.isTraceEnabled()) {
				logger.trace(String.format("Processing inlined properties for TestPropertySource attributes %s", attrs));
			}
			String[] attrProps = attrs.getProperties();
			if (attrProps != null) {
				properties.addAll(0, Arrays.asList(attrProps));
			}
			if (!attrs.isInheritProperties()) {
				break;
			}
		}
		return StringUtils.toStringArray(properties);
	}
