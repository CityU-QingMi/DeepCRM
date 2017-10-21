	private GeneratedValue buildGeneratedValue(Element element) {
		Element subElement = element != null ? element.element( "generated-value" ) : null;
		if ( subElement != null ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( GeneratedValue.class );
			String strategy = subElement.attributeValue( "strategy" );
			if ( "TABLE".equalsIgnoreCase( strategy ) ) {
				ad.setValue( "strategy", GenerationType.TABLE );
			}
			else if ( "SEQUENCE".equalsIgnoreCase( strategy ) ) {
				ad.setValue( "strategy", GenerationType.SEQUENCE );
			}
			else if ( "IDENTITY".equalsIgnoreCase( strategy ) ) {
				ad.setValue( "strategy", GenerationType.IDENTITY );
			}
			else if ( "AUTO".equalsIgnoreCase( strategy ) ) {
				ad.setValue( "strategy", GenerationType.AUTO );
			}
			else if ( StringHelper.isNotEmpty( strategy ) ) {
				throw new AnnotationException( "Unknown GenerationType: " + strategy + ". " + SCHEMA_VALIDATION );
			}
			copyStringAttribute( ad, subElement, "generator", false );
			return AnnotationFactory.create( ad );
		}
		else {
			return null;
		}
	}
