	protected XMLContext createXMLContext(@Nullable Resource[] mappingLocations,
			@Nullable Class<?>[] targetClasses, @Nullable String[] targetPackages)
			throws MappingException, ResolverException, IOException {

		XMLContext context = new XMLContext();
		if (!ObjectUtils.isEmpty(mappingLocations)) {
			Mapping mapping = new Mapping();
			for (Resource mappingLocation : mappingLocations) {
				mapping.loadMapping(SaxResourceUtils.createInputSource(mappingLocation));
			}
			context.addMapping(mapping);
		}
		if (!ObjectUtils.isEmpty(targetClasses)) {
			context.addClasses(targetClasses);
		}
		if (!ObjectUtils.isEmpty(targetPackages)) {
			context.addPackages(targetPackages);
		}
		if (this.castorProperties != null) {
			this.castorProperties.forEach(context::setProperty);
		}
		return context;
	}
