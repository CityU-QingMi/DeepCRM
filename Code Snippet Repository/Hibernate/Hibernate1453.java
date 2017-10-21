	private void getEmbeddedId(List<Annotation> annotationList, XMLContext.Default defaults) {
		for ( Element element : elementsForProperty ) {
			if ( "embedded-id".equals( element.getName() ) ) {
				if ( isProcessingId( defaults ) ) {
					Annotation annotation = getAttributeOverrides( element, defaults, false );
					addIfNotNull( annotationList, annotation );
					annotation = getAssociationOverrides( element, defaults, false );
					addIfNotNull( annotationList, annotation );
					AnnotationDescriptor ad = new AnnotationDescriptor( EmbeddedId.class );
					annotationList.add( AnnotationFactory.create( ad ) );
					getAccessType( annotationList, element );
				}
			}
		}
		if ( elementsForProperty.size() == 0 && defaults.canUseJavaAnnotations() ) {
			Annotation annotation = getPhysicalAnnotation( EmbeddedId.class );
			if ( annotation != null ) {
				annotationList.add( annotation );
				annotation = getPhysicalAnnotation( Column.class );
				addIfNotNull( annotationList, annotation );
				annotation = getPhysicalAnnotation( Columns.class );
				addIfNotNull( annotationList, annotation );
				annotation = getPhysicalAnnotation( GeneratedValue.class );
				addIfNotNull( annotationList, annotation );
				annotation = getPhysicalAnnotation( Temporal.class );
				addIfNotNull( annotationList, annotation );
				annotation = getPhysicalAnnotation( TableGenerator.class );
				addIfNotNull( annotationList, annotation );
				annotation = getPhysicalAnnotation( SequenceGenerator.class );
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
	}
