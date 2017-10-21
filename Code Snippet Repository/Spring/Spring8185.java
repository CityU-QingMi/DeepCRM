	@Override
	public ApplicationContext loadContext(MergedContextConfiguration mergedConfig) throws Exception {
		Assert.notNull(mergedConfig, "mergedConfig must not be null");
		List<SmartContextLoader> candidates = Arrays.asList(getXmlLoader(), getAnnotationConfigLoader());

		Assert.state(!(mergedConfig.hasLocations() && mergedConfig.hasClasses()), () -> String.format(
				"Neither %s nor %s supports loading an ApplicationContext from %s: "
						+ "declare either 'locations' or 'classes' but not both.", name(getXmlLoader()),
				name(getAnnotationConfigLoader()), mergedConfig));

		for (SmartContextLoader loader : candidates) {
			// Determine if each loader can load a context from the mergedConfig. If it
			// can, let it; otherwise, keep iterating.
			if (supports(loader, mergedConfig)) {
				return delegateLoading(loader, mergedConfig);
			}
		}

		// If neither of the candidates supports the mergedConfig based on resources but
		// ACIs or customizers were declared, then delegate to the annotation config
		// loader.
		if (!mergedConfig.getContextInitializerClasses().isEmpty() || !mergedConfig.getContextCustomizers().isEmpty()) {
			return delegateLoading(getAnnotationConfigLoader(), mergedConfig);
		}

		// else...
		throw new IllegalStateException(String.format(
			"Neither %s nor %s was able to load an ApplicationContext from %s.", name(getXmlLoader()),
			name(getAnnotationConfigLoader()), mergedConfig));
	}
