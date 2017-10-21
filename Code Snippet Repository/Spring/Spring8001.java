	public Class<?>[] scanPackages() throws UncategorizedMappingException {
		try {
			List<Class<?>> jaxb2Classes = new ArrayList<>();
			for (String packageToScan : this.packagesToScan) {
				String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
						ClassUtils.convertClassNameToResourcePath(packageToScan) + RESOURCE_PATTERN;
				Resource[] resources = this.resourcePatternResolver.getResources(pattern);
				MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
				for (Resource resource : resources) {
					MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
					if (isJaxb2Class(metadataReader, metadataReaderFactory)) {
						String className = metadataReader.getClassMetadata().getClassName();
						Class<?> jaxb2AnnotatedClass =
								ClassUtils.forName(className, this.resourcePatternResolver.getClassLoader());
						jaxb2Classes.add(jaxb2AnnotatedClass);
					}
				}
			}
			return jaxb2Classes.toArray(new Class<?>[jaxb2Classes.size()]);
		}
		catch (IOException ex) {
			throw new UncategorizedMappingException("Failed to scan classpath for unlisted classes", ex);
		}
		catch (ClassNotFoundException ex) {
			throw new UncategorizedMappingException("Failed to load annotated classes from classpath", ex);
		}
	}
