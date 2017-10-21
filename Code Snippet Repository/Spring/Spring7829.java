	private void scanPackage(SpringPersistenceUnitInfo scannedUnit, String pkg) {
		if (this.componentsIndex != null) {
			Set<String> candidates = new HashSet<>();
			for (AnnotationTypeFilter filter : entityTypeFilters) {
				candidates.addAll(this.componentsIndex.getCandidateTypes(pkg, filter.getAnnotationType().getName()));
			}
			candidates.forEach(scannedUnit::addManagedClassName);
			Set<String> managedPackages = this.componentsIndex.getCandidateTypes(pkg, "package-info");
			managedPackages.forEach(scannedUnit::addManagedPackage);
			return;
		}

		try {
			String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
					ClassUtils.convertClassNameToResourcePath(pkg) + CLASS_RESOURCE_PATTERN;
			Resource[] resources = this.resourcePatternResolver.getResources(pattern);
			MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					MetadataReader reader = readerFactory.getMetadataReader(resource);
					String className = reader.getClassMetadata().getClassName();
					if (matchesFilter(reader, readerFactory)) {
						scannedUnit.addManagedClassName(className);
						if (scannedUnit.getPersistenceUnitRootUrl() == null) {
							URL url = resource.getURL();
							if (ResourceUtils.isJarURL(url)) {
								scannedUnit.setPersistenceUnitRootUrl(ResourceUtils.extractJarFileURL(url));
							}
						}
					}
					else if (className.endsWith(PACKAGE_INFO_SUFFIX)) {
						scannedUnit.addManagedPackage(
								className.substring(0, className.length() - PACKAGE_INFO_SUFFIX.length()));
					}
				}
			}
		}
		catch (IOException ex) {
			throw new PersistenceException("Failed to scan classpath for unlisted entity classes", ex);
		}
	}
