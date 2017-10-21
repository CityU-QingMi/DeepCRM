	private void finishBindingCompositeIdentifier(
			MappingDocument sourceDocument,
			RootClass rootEntityDescriptor,
			CompositeIdentifierSource identifierSource,
			Component cid,
			String propertyName) {
		if ( propertyName == null ) {
			rootEntityDescriptor.setEmbeddedIdentifier( cid.isEmbedded() );
			if ( cid.isEmbedded() ) {
				// todo : what is the implication of this?
				cid.setDynamic( !rootEntityDescriptor.hasPojoRepresentation() );
/**/
/**/
/**/
/**/
/**/
			}
		}
		else {
			Property prop = new Property();
			prop.setValue( cid );
			bindProperty(
					sourceDocument,
					( (IdentifierSourceAggregatedComposite) identifierSource ).getIdentifierAttributeSource(),
					prop
			);
			rootEntityDescriptor.setIdentifierProperty( prop );
			rootEntityDescriptor.setDeclaredIdentifierProperty( prop );
		}

		makeIdentifier(
				sourceDocument,
				identifierSource.getIdentifierGeneratorDescriptor(),
				null,
				cid
		);
	}
