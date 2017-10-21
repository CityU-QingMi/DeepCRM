	private void getEmbedded(List<Annotation> annotationList, XMLContext.Default defaults) {
		for ( Element element : elementsForProperty ) {
			if ( "embedded".equals( element.getName() ) ) {
				AnnotationDescriptor ad = new AnnotationDescriptor( Embedded.class );
				annotationList.add( AnnotationFactory.create( ad ) );
				Annotation annotation = getAttributeOverrides( element, defaults, false );
				addIfNotNull( annotationList, annotation );
				annotation = getAssociationOverrides( element, defaults, false );
				addIfNotNull( annotationList, annotation );
				getAccessType( annotationList, element );
			}
		}
		if ( elementsForProperty.size() == 0 && defaults.canUseJavaAnnotations() ) {
			Annotation annotation = getPhysicalAnnotation( Embedded.class );
			if ( annotation != null ) {
				annotationList.add( annotation );
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
	}
