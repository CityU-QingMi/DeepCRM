	private void getBasic(List<Annotation> annotationList, XMLContext.Default defaults) {
		for ( Element element : elementsForProperty ) {
			if ( "basic".equals( element.getName() ) ) {
				Annotation annotation = buildColumns( element );
				addIfNotNull( annotationList, annotation );
				getAccessType( annotationList, element );
				getTemporal( annotationList, element );
				getLob( annotationList, element );
				getEnumerated( annotationList, element );
				AnnotationDescriptor basic = new AnnotationDescriptor( Basic.class );
				getFetchType( basic, element );
				copyBooleanAttribute( basic, element, "optional" );
				annotationList.add( AnnotationFactory.create( basic ) );
			}
		}
		if ( elementsForProperty.size() == 0 && defaults.canUseJavaAnnotations() ) {
			//no annotation presence constraint, basic is the default
			Annotation annotation = getPhysicalAnnotation( Basic.class );
			addIfNotNull( annotationList, annotation );
			annotation = getPhysicalAnnotation( Lob.class );
			addIfNotNull( annotationList, annotation );
			annotation = getPhysicalAnnotation( Enumerated.class );
			addIfNotNull( annotationList, annotation );
			annotation = getPhysicalAnnotation( Temporal.class );
			addIfNotNull( annotationList, annotation );
			annotation = getPhysicalAnnotation( Column.class );
			addIfNotNull( annotationList, annotation );
			annotation = getPhysicalAnnotation( Columns.class );
			addIfNotNull( annotationList, annotation );
			annotation = getPhysicalAnnotation( AttributeOverride.class );
			addIfNotNull( annotationList, annotation );
			annotation = getPhysicalAnnotation( AttributeOverrides.class );
			addIfNotNull( annotationList, annotation );
			annotation = getPhysicalAnnotation( AssociationOverride.class );
			addIfNotNull( annotationList, annotation );
			annotation = getPhysicalAnnotation( AssociationOverrides.class );
			addIfNotNull( annotationList, annotation );
		}
	}
