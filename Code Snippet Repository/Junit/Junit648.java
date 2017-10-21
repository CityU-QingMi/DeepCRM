	private void addFilters(LauncherDiscoveryRequestBuilder requestBuilder, CommandLineOptions options) {
		requestBuilder.filters(includeClassNamePatterns(options.getIncludedClassNamePatterns().toArray(new String[0])));

		if (!options.getExcludedClassNamePatterns().isEmpty()) {
			requestBuilder.filters(
				excludeClassNamePatterns(options.getExcludedClassNamePatterns().toArray(new String[0])));
		}

		if (!options.getIncludedPackages().isEmpty()) {
			requestBuilder.filters(includePackageNames(options.getIncludedPackages()));
		}

		if (!options.getExcludedPackages().isEmpty()) {
			requestBuilder.filters(excludePackageNames(options.getExcludedPackages()));
		}

		if (!options.getIncludedTags().isEmpty()) {
			requestBuilder.filters(includeTags(options.getIncludedTags()));
		}

		if (!options.getExcludedTags().isEmpty()) {
			requestBuilder.filters(excludeTags(options.getExcludedTags()));
		}

		if (!options.getIncludedEngines().isEmpty()) {
			requestBuilder.filters(includeEngines(options.getIncludedEngines()));
		}

		if (!options.getExcludedEngines().isEmpty()) {
			requestBuilder.filters(excludeEngines(options.getExcludedEngines()));
		}
	}
