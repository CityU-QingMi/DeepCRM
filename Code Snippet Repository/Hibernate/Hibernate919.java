	private void bindEntityIdentifier(
			MappingDocument mappingDocument,
			EntityHierarchySourceImpl hierarchySource,
			RootClass rootEntityDescriptor) {
		switch ( hierarchySource.getIdentifierSource().getNature() ) {
			case SIMPLE: {
				bindSimpleEntityIdentifier(
						mappingDocument,
						hierarchySource,
						rootEntityDescriptor
				);
				break;
			}
			case AGGREGATED_COMPOSITE: {
				bindAggregatedCompositeEntityIdentifier(
						mappingDocument,
						hierarchySource,
						rootEntityDescriptor
				);
				break;
			}
			case NON_AGGREGATED_COMPOSITE: {
				bindNonAggregatedCompositeEntityIdentifier(
						mappingDocument,
						hierarchySource,
						rootEntityDescriptor
				);
				break;
			}
			default: {
				throw new MappingException(
						String.format(
								Locale.ENGLISH,
								"Unexpected entity identifier nature [%s] for entity %s",
								hierarchySource.getIdentifierSource().getNature(),
								hierarchySource.getRoot().getEntityNamingSource().getEntityName()
						),
						mappingDocument.getOrigin()
				);
			}
		}
	}
