	private Identifier determineTable(
			MappingDocument mappingDocument,
			SingularAttributeSourceEmbedded embeddedAttributeSource) {
		Identifier tableName = null;
		for ( AttributeSource attributeSource : embeddedAttributeSource.getEmbeddableSource().attributeSources() ) {
			final Identifier determinedName;
			if ( RelationalValueSourceContainer.class.isInstance( attributeSource ) ) {
				determinedName = determineTable(
						mappingDocument,
						embeddedAttributeSource.getAttributeRole().getFullPath(),
						(RelationalValueSourceContainer) attributeSource

				);
			}
			else if ( SingularAttributeSourceEmbedded.class.isInstance( attributeSource ) ) {
				determinedName = determineTable( mappingDocument, (SingularAttributeSourceEmbedded) attributeSource );
			}
			else if ( SingularAttributeSourceAny.class.isInstance( attributeSource ) ) {
				determinedName = determineTable(
						mappingDocument,
						attributeSource.getAttributeRole().getFullPath(),
						( (SingularAttributeSourceAny) attributeSource ).getKeySource().getRelationalValueSources()
				);
			}
			else {
				continue;
			}

			if (  EqualsHelper.equals( tableName, determinedName ) ) {
				continue;
			}

			if ( tableName != null ) {
				throw new MappingException(
						String.format(
								Locale.ENGLISH,
								"Attribute [%s] referenced columns from multiple tables: %s, %s",
								embeddedAttributeSource.getAttributeRole().getFullPath(),
								tableName,
								determinedName
						),
						mappingDocument.getOrigin()
				);
			}

			tableName = determinedName;
		}

		return tableName;
	}
