	private Map<String, String> getConfigurationParameters() {
		String content = parameters.getProviderProperties().get(CONFIGURATION_PARAMETERS);
		if (content == null) {
			return emptyMap();
		}
		try (StringReader reader = new StringReader(content)) {
			Map<String, String> result = new HashMap<>();
			Properties props = new Properties();
			props.load(reader);
			props.stringPropertyNames().forEach(key -> result.put(key, props.getProperty(key)));
			return result;
		}
		catch (IOException ex) {
			throw new UncheckedIOException("Error reading " + CONFIGURATION_PARAMETERS, ex);
		}
	}
