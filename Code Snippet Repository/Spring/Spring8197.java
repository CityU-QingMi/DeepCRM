	@Override
	protected void validateMergedContextConfiguration(MergedContextConfiguration mergedConfig) {
		if (mergedConfig.hasLocations()) {
			String msg = String.format(
				"Test class [%s] has been configured with @ContextConfiguration's 'locations' (or 'value') attribute %s, "
						+ "but %s does not support resource locations.", mergedConfig.getTestClass().getName(),
				ObjectUtils.nullSafeToString(mergedConfig.getLocations()), getClass().getSimpleName());
			logger.error(msg);
			throw new IllegalStateException(msg);
		}
	}
