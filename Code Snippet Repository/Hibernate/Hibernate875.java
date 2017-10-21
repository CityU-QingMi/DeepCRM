	@Override
	public void processEntityHierarchies(Set<String> processedEntityNames) {
		hierarchy_loop : for ( EntityHierarchySourceImpl entityHierarchy : entityHierarchies ) {
			for ( String entityName : entityHierarchy.getContainedEntityNames() ) {
				if ( processedEntityNames.contains( entityName ) ) {
					log.debugf(
							"Skipping HBM processing of entity hierarchy [%s], as at least one entity [%s] has been processed",
							entityHierarchy.getRoot().getEntityNamingSource().getEntityName(),
							entityName
					);
					continue hierarchy_loop;
				}
			}

			modelBinder.bindEntityHierarchy( entityHierarchy );
			processedEntityNames.addAll( entityHierarchy.getContainedEntityNames() );
		}
	}
