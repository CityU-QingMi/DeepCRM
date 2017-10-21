	@Override
	public ClassMetadata getClassMetadataFor(Class<?> clazz) {
		try {
			MetadataReader reader =
				new SimpleMetadataReaderFactory().getMetadataReader(clazz.getName());
			return reader.getAnnotationMetadata();
		}
		catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}
