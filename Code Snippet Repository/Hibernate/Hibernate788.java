	public static ManagedResourcesImpl baseline(MetadataSources sources, MetadataBuildingOptions metadataBuildingOptions) {
		final ManagedResourcesImpl impl = new ManagedResourcesImpl();
		for ( AttributeConverterDefinition attributeConverterDefinition : metadataBuildingOptions.getAttributeConverters() ) {
			impl.addAttributeConverterDefinition( attributeConverterDefinition );
		}
		impl.annotatedClassReferences.addAll( sources.getAnnotatedClasses() );
		impl.annotatedClassNames.addAll( sources.getAnnotatedClassNames() );
		impl.annotatedPackageNames.addAll( sources.getAnnotatedPackages() );
		impl.mappingFileBindings.addAll( sources.getXmlBindings() );
		return impl;
	}
