	private Access getAccessType(Element tree, XMLContext.Default defaults) {
		String access = tree == null ? null : tree.attributeValue( "access" );
		if ( access != null ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( Access.class );
			AccessType type;
			try {
				type = AccessType.valueOf( access );
			}
			catch ( IllegalArgumentException e ) {
				throw new AnnotationException( access + " is not a valid access type. Check you xml confguration." );
			}
			ad.setValue( "value", type );
			return AnnotationFactory.create( ad );
		}
		else if ( defaults.canUseJavaAnnotations() && isPhysicalAnnotationPresent( Access.class ) ) {
			return getPhysicalAnnotation( Access.class );
		}
		else if ( defaults.getAccess() != null ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( Access.class );
			ad.setValue( "value", defaults.getAccess() );
			return AnnotationFactory.create( ad );
		}
		else {
			return null;
		}
	}
