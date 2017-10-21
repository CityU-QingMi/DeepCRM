	@Test
	public void assertContextConfigurationLocations() throws Exception {

		final ContextConfiguration contextConfig = this.testClass.getAnnotation(ContextConfiguration.class);
		final ContextLoader contextLoader = new GenericXmlContextLoader();
		final String[] configuredLocations = (String[]) AnnotationUtils.getValue(contextConfig);
		final String[] processedLocations = contextLoader.processLocations(this.testClass, configuredLocations);

		if (logger.isDebugEnabled()) {
			logger.debug("----------------------------------------------------------------------");
			logger.debug("Configured locations: " + ObjectUtils.nullSafeToString(configuredLocations));
			logger.debug("Expected   locations: " + ObjectUtils.nullSafeToString(this.expectedLocations));
			logger.debug("Processed  locations: " + ObjectUtils.nullSafeToString(processedLocations));
		}

		assertArrayEquals("Verifying locations for test [" + this.testClass + "].", this.expectedLocations,
			processedLocations);
	}
