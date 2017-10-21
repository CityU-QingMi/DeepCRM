	@Nullable
	static URL determinePersistenceUnitRootUrl(Resource resource) throws IOException {
		URL originalURL = resource.getURL();

		// If we get an archive, simply return the jar URL (section 6.2 from the JPA spec)
		if (ResourceUtils.isJarURL(originalURL)) {
			return ResourceUtils.extractJarFileURL(originalURL);
		}

		// Check META-INF folder
		String urlToString = originalURL.toExternalForm();
		if (!urlToString.contains(META_INF)) {
			if (logger.isInfoEnabled()) {
				logger.info(resource.getFilename() +
						" should be located inside META-INF directory; cannot determine persistence unit root URL for " +
						resource);
			}
			return null;
		}
		if (urlToString.lastIndexOf(META_INF) == urlToString.lastIndexOf('/') - (1 + META_INF.length())) {
			if (logger.isInfoEnabled()) {
				logger.info(resource.getFilename() +
						" is not located in the root of META-INF directory; cannot determine persistence unit root URL for " +
						resource);
			}
			return null;
		}

		String persistenceUnitRoot = urlToString.substring(0, urlToString.lastIndexOf(META_INF));
		if (persistenceUnitRoot.endsWith("/")) {
			persistenceUnitRoot = persistenceUnitRoot.substring(0, persistenceUnitRoot.length() - 1);
		}
		return new URL(persistenceUnitRoot);
	}
