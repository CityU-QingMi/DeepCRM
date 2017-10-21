	private PrimaryKeyJoinColumns getPrimaryKeyJoinColumns(Element element, XMLContext.Default defaults, boolean mergeWithAnnotations) {
		PrimaryKeyJoinColumn[] columns = buildPrimaryKeyJoinColumns( element );
		if ( mergeWithAnnotations ) {
			if ( columns.length == 0 && defaults.canUseJavaAnnotations() ) {
				PrimaryKeyJoinColumn annotation = getPhysicalAnnotation( PrimaryKeyJoinColumn.class );
				if ( annotation != null ) {
					columns = new PrimaryKeyJoinColumn[] { annotation };
				}
				else {
					PrimaryKeyJoinColumns annotations = getPhysicalAnnotation( PrimaryKeyJoinColumns.class );
					columns = annotations != null ? annotations.value() : columns;
				}
			}
		}
		if ( columns.length > 0 ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( PrimaryKeyJoinColumns.class );
			ad.setValue( "value", columns );
			return AnnotationFactory.create( ad );
		}
		else {
			return null;
		}
	}
