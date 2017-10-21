	private void addAttributeOverrideIfNeeded(AttributeOverride annotation, List<AttributeOverride> overrides) {
		if ( annotation != null ) {
			String overrideName = annotation.name();
			boolean present = false;
			for ( AttributeOverride current : overrides ) {
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
