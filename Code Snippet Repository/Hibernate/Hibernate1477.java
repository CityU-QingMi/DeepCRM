	private static EntityResult buildEntityResult(
			Element entityResultElement,
			XMLContext.Default defaults,
			ClassLoaderAccess classLoaderAccess) {
		final AnnotationDescriptor entityResultDescriptor = new AnnotationDescriptor( EntityResult.class );

		final Class entityClass = resolveClassReference( entityResultElement.attributeValue( "entity-class" ), defaults, classLoaderAccess );
		entityResultDescriptor.setValue( "entityClass", entityClass );

		copyStringAttribute( entityResultDescriptor, entityResultElement, "discriminator-column", false );

		// process the <field-result/> sub-elements
		List<FieldResult> fieldResultAnnotations = new ArrayList<FieldResult>();
		for ( Element fieldResult : (List<Element>) entityResultElement.elements( "field-result" ) ) {
			AnnotationDescriptor fieldResultDescriptor = new AnnotationDescriptor( FieldResult.class );
			copyStringAttribute( fieldResultDescriptor, fieldResult, "name", true );
			copyStringAttribute( fieldResultDescriptor, fieldResult, "column", true );
			fieldResultAnnotations.add( (FieldResult) AnnotationFactory.create( fieldResultDescriptor ) );
		}
		entityResultDescriptor.setValue(
				"fields", fieldResultAnnotations.toArray( new FieldResult[fieldResultAnnotations.size()] )
		);
		return AnnotationFactory.create( entityResultDescriptor );
	}
