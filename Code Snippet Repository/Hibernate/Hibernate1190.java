	private static AssociationOverride[] buildAssociationOverrides(XAnnotatedElement element, String path) {
		AssociationOverride singleOverride = element.getAnnotation( AssociationOverride.class );
		AssociationOverrides pluralOverrides = element.getAnnotation( AssociationOverrides.class );

		AssociationOverride[] overrides;
		if ( singleOverride != null ) {
			overrides = new AssociationOverride[] { singleOverride };
		}
		else if ( pluralOverrides != null ) {
			overrides = pluralOverrides.value();
		}
		else {
			overrides = null;
		}
		return overrides;
	}
