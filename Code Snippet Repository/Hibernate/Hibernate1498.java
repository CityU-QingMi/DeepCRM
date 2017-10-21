	private DiscriminatorColumn getDiscriminatorColumn(Element tree, XMLContext.Default defaults) {
		Element element = tree != null ? tree.element( "discriminator-column" ) : null;
		if ( element != null ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( DiscriminatorColumn.class );
			copyStringAttribute( ad, element, "name", false );
			copyStringAttribute( ad, element, "column-definition", false );
			String value = element.attributeValue( "discriminator-type" );
			DiscriminatorType type = DiscriminatorType.STRING;
			if ( value != null ) {
				if ( "STRING".equals( value ) ) {
					type = DiscriminatorType.STRING;
				}
				else if ( "CHAR".equals( value ) ) {
					type = DiscriminatorType.CHAR;
				}
				else if ( "INTEGER".equals( value ) ) {
					type = DiscriminatorType.INTEGER;
				}
				else {
					throw new AnnotationException(
							"Unknown DiscrimiatorType in XML: " + value + " (" + SCHEMA_VALIDATION + ")"
					);
				}
			}
			ad.setValue( "discriminatorType", type );
			copyIntegerAttribute( ad, element, "length" );
			return AnnotationFactory.create( ad );
		}
		else if ( defaults.canUseJavaAnnotations() ) {
			return getPhysicalAnnotation( DiscriminatorColumn.class );
		}
		else {
			return null;
		}
	}
