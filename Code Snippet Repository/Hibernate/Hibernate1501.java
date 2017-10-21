	private Inheritance getInheritance(Element tree, XMLContext.Default defaults) {
		Element element = tree != null ? tree.element( "inheritance" ) : null;
		if ( element != null ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( Inheritance.class );
			Attribute attr = element.attribute( "strategy" );
			InheritanceType strategy = InheritanceType.SINGLE_TABLE;
			if ( attr != null ) {
				String value = attr.getValue();
				if ( "SINGLE_TABLE".equals( value ) ) {
					strategy = InheritanceType.SINGLE_TABLE;
				}
				else if ( "JOINED".equals( value ) ) {
					strategy = InheritanceType.JOINED;
				}
				else if ( "TABLE_PER_CLASS".equals( value ) ) {
					strategy = InheritanceType.TABLE_PER_CLASS;
				}
				else {
					throw new AnnotationException(
							"Unknown InheritanceType in XML: " + value + " (" + SCHEMA_VALIDATION + ")"
					);
				}
			}
			ad.setValue( "strategy", strategy );
			return AnnotationFactory.create( ad );
		}
		else if ( defaults.canUseJavaAnnotations() ) {
			return getPhysicalAnnotation( Inheritance.class );
		}
		else {
			return null;
		}
	}
