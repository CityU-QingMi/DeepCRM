	private static ConstructorResult buildConstructorResult(
			Element constructorResultElement,
			XMLContext.Default defaults,
			ClassLoaderAccess classLoaderAccess) {
		AnnotationDescriptor constructorResultDescriptor = new AnnotationDescriptor( ConstructorResult.class );

		final Class entityClass = resolveClassReference( constructorResultElement.attributeValue( "target-class" ), defaults, classLoaderAccess );
		constructorResultDescriptor.setValue( "targetClass", entityClass );

		List<ColumnResult> columnResultAnnotations = new ArrayList<ColumnResult>();
		for ( Element columnResultElement : (List<Element>) constructorResultElement.elements( "column" ) ) {
			columnResultAnnotations.add( buildColumnResult( columnResultElement, defaults, classLoaderAccess ) );
		}
		constructorResultDescriptor.setValue(
				"columns",
				columnResultAnnotations.toArray( new ColumnResult[ columnResultAnnotations.size() ] )
		);

		return AnnotationFactory.create( constructorResultDescriptor );
	}
