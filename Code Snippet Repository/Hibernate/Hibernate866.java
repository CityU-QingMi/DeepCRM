	private static VersionAttributeSource interpretVersionSource(RootEntitySourceImpl rootEntitySource) {
		final JaxbHbmRootEntityType entityElement = rootEntitySource.jaxbEntityMapping();
		if ( entityElement.getVersion() != null ) {
			return new VersionAttributeSourceImpl(
					rootEntitySource.sourceMappingDocument(),
					rootEntitySource,
					entityElement.getVersion()
			);
		}
		else if ( entityElement.getTimestamp() != null ) {
			return new TimestampAttributeSourceImpl(
					rootEntitySource.sourceMappingDocument(),
					rootEntitySource,
					entityElement.getTimestamp()
			);
		}
		return null;
	}
