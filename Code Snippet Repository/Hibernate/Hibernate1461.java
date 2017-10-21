	private void getAccessType(List<Annotation> annotationList, Element element) {
		if ( element == null ) {
			return;
		}
		String access = element.attributeValue( "access" );
		if ( access != null ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( Access.class );
			AccessType type;
			try {
				type = AccessType.valueOf( access );
			}
			catch ( IllegalArgumentException e ) {
				throw new AnnotationException( access + " is not a valid access type. Check you xml confguration." );
			}

			if ( ( AccessType.PROPERTY.equals( type ) && this.element instanceof Method ) ||
					( AccessType.FIELD.equals( type ) && this.element instanceof Field ) ) {
				return;
			}

			ad.setValue( "value", type );
			annotationList.add( AnnotationFactory.create( ad ) );
		}
	}
