	private List<DiscoverySelector> createExplicitDiscoverySelectors(CommandLineOptions options) {
		List<DiscoverySelector> selectors = new ArrayList<>();
		options.getSelectedUris().stream().map(DiscoverySelectors::selectUri).forEach(selectors::add);
		options.getSelectedFiles().stream().map(DiscoverySelectors::selectFile).forEach(selectors::add);
		options.getSelectedDirectories().stream().map(DiscoverySelectors::selectDirectory).forEach(selectors::add);
		options.getSelectedPackages().stream().map(DiscoverySelectors::selectPackage).forEach(selectors::add);
		options.getSelectedClasses().stream().map(DiscoverySelectors::selectClass).forEach(selectors::add);
		options.getSelectedMethods().stream().map(DiscoverySelectors::selectMethod).forEach(selectors::add);
		options.getSelectedClasspathResources().stream().map(DiscoverySelectors::selectClasspathResource).forEach(
			selectors::add);
		Preconditions.notEmpty(selectors, "No arguments were supplied to the ConsoleLauncher");
		return selectors;
	}
