	private static String detectDefaultPropertiesFile(Class<?> testClass) {
		String resourcePath = ClassUtils.convertClassNameToResourcePath(testClass.getName()) + ".properties";
		String prefixedResourcePath = ResourceUtils.CLASSPATH_URL_PREFIX + resourcePath;
		ClassPathResource classPathResource = new ClassPathResource(resourcePath);

		if (classPathResource.exists()) {
			if (logger.isInfoEnabled()) {
				logger.info(String.format("Detected default properties file \"%s\" for test class [%s]",
					prefixedResourcePath, testClass.getName()));
			}
			return prefixedResourcePath;
		}
		else {
			String msg = String.format("Could not detect default properties file for test [%s]: " +
					"%s does not exist. Either declare the 'locations' or 'properties' attributes " +
					"of @TestPropertySource or make the default properties file available.", testClass.getName(),
					classPathResource);
			logger.error(msg);
			throw new IllegalStateException(msg);
		}
	}
