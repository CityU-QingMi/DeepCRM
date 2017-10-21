	private static void ensureSpringRulesAreNotPresent(Class<?> testClass) {
		for (Field field : testClass.getFields()) {
			Assert.state(!SpringClassRule.class.isAssignableFrom(field.getType()), () -> String.format(
					"Detected SpringClassRule field in test class [%s], " +
					"but SpringClassRule cannot be used with the SpringJUnit4ClassRunner.", testClass.getName()));
			Assert.state(!SpringMethodRule.class.isAssignableFrom(field.getType()), () -> String.format(
					"Detected SpringMethodRule field in test class [%s], " +
					"but SpringMethodRule cannot be used with the SpringJUnit4ClassRunner.", testClass.getName()));
		}
	}
