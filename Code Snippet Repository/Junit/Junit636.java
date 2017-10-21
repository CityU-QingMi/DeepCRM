	CommandLineOptions toCommandLineOptions(OptionSet detectedOptions) {

		CommandLineOptions result = new CommandLineOptions();

		// General Purpose
		result.setDisplayHelp(detectedOptions.has(this.help));
		result.setAnsiColorOutputDisabled(detectedOptions.has(this.disableAnsiColors));
		result.setDetails(detectedOptions.valueOf(this.details));
		result.setTheme(detectedOptions.valueOf(this.theme));
		result.setAdditionalClasspathEntries(detectedOptions.valuesOf(this.additionalClasspathEntries));

		// Reports
		result.setReportsDir(detectedOptions.valueOf(this.reportsDir));

		// Selectors
		result.setScanClasspath(detectedOptions.has(this.selectedClasspathEntries));
		result.setSelectedClasspathEntries(detectedOptions.valuesOf(this.selectedClasspathEntries));
		result.setSelectedUris(detectedOptions.valuesOf(this.selectedUris));
		result.setSelectedFiles(detectedOptions.valuesOf(this.selectedFiles));
		result.setSelectedDirectories(detectedOptions.valuesOf(this.selectedDirectories));
		result.setSelectedPackages(detectedOptions.valuesOf(this.selectedPackages));
		result.setSelectedClasses(detectedOptions.valuesOf(this.selectedClasses));
		result.setSelectedMethods(detectedOptions.valuesOf(this.selectedMethods));
		result.setSelectedClasspathResources(detectedOptions.valuesOf(this.selectedClasspathResources));

		// Filters
		result.setIncludedClassNamePatterns(detectedOptions.valuesOf(this.includeClassNamePattern));
		result.setExcludedClassNamePatterns(detectedOptions.valuesOf(this.excludeClassNamePattern));
		result.setIncludedPackages(detectedOptions.valuesOf(this.includePackage));
		result.setExcludedPackages(detectedOptions.valuesOf(this.excludePackage));
		result.setIncludedTags(detectedOptions.valuesOf(this.includeTag));
		result.setExcludedTags(detectedOptions.valuesOf(this.excludeTag));
		result.setIncludedEngines(detectedOptions.valuesOf(this.includeEngine));
		result.setExcludedEngines(detectedOptions.valuesOf(this.excludeEngine));

		// Configuration Parameters
		Map<String, String> configurationParametersMap = detectedOptions.valuesOf(
			this.configurationParameters).stream().collect(toMap(pair -> pair.key, pair -> pair.value));
		result.setConfigurationParameters(configurationParametersMap);

		return result;
	}
