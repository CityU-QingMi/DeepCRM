	private void addAssociationOverrideIfNeeded(AssociationOverride annotation, List<AssociationOverride> overrides) {
		if ( annotation != null ) {
			String overrideName = annotation.name();
			boolean present = false;
			for ( AssociationOverride current : overrides ) {
				if ( current.name().equals( overrideName ) ) {
					present = true;
					break;
				}
			}
			if ( !present ) {
				overrides.add( annotation );
			}
		}
	}
