	private static Properties fromClasspathResource(String configFileName) {
		Properties props = new Properties();

		try {
			ClassLoader classLoader = ClassLoaderUtils.getDefaultClassLoader();
			List<URL> resources = Collections.list(classLoader.getResources(configFileName));

			if (!resources.isEmpty()) {
				if (resources.size() > 1) {
					logger.warn(() -> String.format(
						"Discovered %d '%s' configuration files in the classpath; only the first will be used.",
						resources.size(), configFileName));
				}

				URL configFileUrl = resources.get(0);
				logger.info(() -> String.format(
					"Loading JUnit Platform configuration parameters from classpath resource [%s].", configFileUrl));
				try (InputStream inputStream = configFileUrl.openStream()) {
					props.load(inputStream);
				}
			}
		}
		catch (Exception ex) {
			logger.warn(ex,
				() -> String.format(
					"Failed to load JUnit Platform configuration parameters from classpath resource [%s].",
					configFileName));
		}

		return props;
	}
