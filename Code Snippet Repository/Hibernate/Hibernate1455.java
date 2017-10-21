	private void getId(List<Annotation> annotationList, XMLContext.Default defaults) {
		for ( Element element : elementsForProperty ) {
			if ( "id".equals( element.getName() ) ) {
				boolean processId = isProcessingId( defaults );
				if ( processId ) {
					Annotation annotation = buildColumns( element );
					addIfNotNull( annotationList, annotation );
					annotation = buildGeneratedValue( element );
					addIfNotNull( annotationList, annotation );
					getTemporal( annotationList, element );
					//FIXME: fix the priority of xml over java for generator names
					annotation = getTableGenerator( element, defaults );
					addIfNotNull( annotationList, annotation );
					annotation = getSequenceGenerator( element, defaults );
					addIfNotNull( annotationList, annotation );
					AnnotationDescriptor id = new AnnotationDescriptor( Id.class );
					annotationList.add( AnnotationFactory.create( id ) );
					getAccessType( annotationList, element );
				}
			}
		}
		if ( elementsForProperty.size() == 0 && defaults.canUseJavaAnnotations() ) {
			Annotation annotation = getPhysicalAnnotation( Id.class );
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
