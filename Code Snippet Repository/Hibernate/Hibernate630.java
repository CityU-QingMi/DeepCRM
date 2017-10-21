	@Override
	@SuppressWarnings("")
	public boolean shouldAutoApplyToAttribute(XProperty xProperty, MetadataBuildingContext context) {
		if ( !autoApply ) {
			return false;
		}

		final ResolvedType attributeType = resolveAttributeType( xProperty, context );
		return typesMatch( domainType, attributeType );
	}
