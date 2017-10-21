	protected String[] generateDefaultLocations(Class<?> clazz) {
		Assert.notNull(clazz, "Class must not be null");

		String[] suffixes = getResourceSuffixes();
		for (String suffix : suffixes) {
			Assert.hasText(suffix, "Resource suffix must not be empty");
			String resourcePath = ClassUtils.convertClassNameToResourcePath(clazz.getName()) + suffix;
			String prefixedResourcePath = ResourceUtils.CLASSPATH_URL_PREFIX + resourcePath;
			ClassPathResource classPathResource = new ClassPathResource(resourcePath);
			if (classPathResource.exists()) {
				if (logger.isInfoEnabled()) {
					logger.info(String.format("Detected default resource location \"%s\" for test class [%s]",
							prefixedResourcePath, clazz.getName()));
				}
				return new String[] {prefixedResourcePath};
			}
			else if (logger.isDebugEnabled()) {
				logger.debug(String.format("Did not detect default resource location for test class [%s]: " +
						"%s does not exist", clazz.getName(), classPathResource));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info(String.format("Could not detect default resource locations for test class [%s]: " +
					"no resource found for suffixes %s.", clazz.getName(), ObjectUtils.nullSafeToString(suffixes)));
		}

		return EMPTY_STRING_ARRAY;
	}
