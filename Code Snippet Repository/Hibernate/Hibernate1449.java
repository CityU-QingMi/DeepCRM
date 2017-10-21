	private void getVersion(List<Annotation> annotationList, XMLContext.Default defaults) {
		for ( Element element : elementsForProperty ) {
			if ( "version".equals( element.getName() ) ) {
				Annotation annotation = buildColumns( element );
				addIfNotNull( annotationList, annotation );
				getTemporal( annotationList, element );
				AnnotationDescriptor basic = new AnnotationDescriptor( Version.class );
				annotationList.add( AnnotationFactory.create( basic ) );
				getAccessType( annotationList, element );
			}
		}
		if ( elementsForProperty.size() == 0 && defaults.canUseJavaAnnotations() ) {
			//we have nothing, so Java annotations might occurs
			Annotation annotation = getPhysicalAnnotation( Version.class );
			if ( annotation != null ) {
				annotationList.add( annotation );
				annotation = getPhysicalAnnotation( Column.class );
				addIfNotNull( annotationList, annotation );
				annotation = getPhysicalAnnotation( Columns.class );
				addIfNotNull( annotationList, annotation );
				annotation = getPhysicalAnnotation( Temporal.class );
				addIfNotNull( annotationList, annotation );
			}
		}
	}
