	private void processJPAIndexHolders(MetadataBuildingContext buildingContext) {
		if ( jpaIndexHoldersByTable == null ) {
			return;
		}

		for ( Table table : jpaIndexHoldersByTable.keySet() ) {
			final List<JPAIndexHolder> jpaIndexHolders = jpaIndexHoldersByTable.get( table );
			for ( JPAIndexHolder holder : jpaIndexHolders ) {
				buildUniqueKeyFromColumnNames(
						table,
						holder.getName(),
						holder.getColumns(),
						holder.getOrdering(),
						holder.isUnique(),
						buildingContext
				);
			}
		}
	}
