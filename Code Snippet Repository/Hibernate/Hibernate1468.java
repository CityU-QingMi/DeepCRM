	private List<AttributeOverride> buildAttributeOverrides(List<Element> subelements, String nodeName) {
		List<AttributeOverride> overrides = new ArrayList<AttributeOverride>();
		if ( subelements != null && subelements.size() > 0 ) {
			for ( Element current : subelements ) {
				if ( !current.getName().equals( nodeName ) ) {
					continue;
				}
				AnnotationDescriptor override = new AnnotationDescriptor( AttributeOverride.class );
				copyStringAttribute( override, current, "name", true );
				Element column = current.element( "column" );
				override.setValue( "column", getColumn( column, true, current ) );
				overrides.add( (AttributeOverride) AnnotationFactory.create( override ) );
			}
		}
		return overrides;
	}
