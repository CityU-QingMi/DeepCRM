	private static ColumnResult buildColumnResult(
			Element columnResultElement,
			XMLContext.Default defaults,
			ClassLoaderAccess classLoaderAccess) {
//		AnnotationDescriptor columnResultDescriptor = new AnnotationDescriptor( ColumnResult.class );
//		copyStringAttribute( columnResultDescriptor, columnResultElement, "name", true );
//		return AnnotationFactory.create( columnResultDescriptor );

		AnnotationDescriptor columnResultDescriptor = new AnnotationDescriptor( ColumnResult.class );
		copyStringAttribute( columnResultDescriptor, columnResultElement, "name", true );
		final String columnTypeName = columnResultElement.attributeValue( "class" );
		if ( StringHelper.isNotEmpty( columnTypeName ) ) {
			columnResultDescriptor.setValue( "type", resolveClassReference( columnTypeName, defaults, classLoaderAccess ) );
		}
		return AnnotationFactory.create( columnResultDescriptor );
	}
