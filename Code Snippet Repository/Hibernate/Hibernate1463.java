	private List<AssociationOverride> buildAssociationOverrides(Element element, XMLContext.Default defaults) {
		List<Element> subelements = element == null ? null : element.elements( "association-override" );
		List<AssociationOverride> overrides = new ArrayList<AssociationOverride>();
		if ( subelements != null && subelements.size() > 0 ) {
			for ( Element current : subelements ) {
				AnnotationDescriptor override = new AnnotationDescriptor( AssociationOverride.class );
				copyStringAttribute( override, current, "name", true );
				override.setValue( "joinColumns", getJoinColumns( current, false ) );
				JoinTable joinTable = buildJoinTable( current, defaults );
				if ( joinTable != null ) {
					override.setValue( "joinTable", joinTable );
				}
				overrides.add( (AssociationOverride) AnnotationFactory.create( override ) );
			}
		}
		return overrides;
	}
