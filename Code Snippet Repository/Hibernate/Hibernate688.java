	private void processUniqueConstraintHolders(MetadataBuildingContext buildingContext) {
		if ( uniqueConstraintHoldersByTable == null ) {
			return;
		}

		for ( Map.Entry<Table, List<UniqueConstraintHolder>> tableListEntry : uniqueConstraintHoldersByTable.entrySet() ) {
			final Table table = tableListEntry.getKey();
			final List<UniqueConstraintHolder> uniqueConstraints = tableListEntry.getValue();
			for ( UniqueConstraintHolder holder : uniqueConstraints ) {
				buildUniqueKeyFromColumnNames( table, holder.getName(), holder.getColumns(), buildingContext );
			}
		}

		uniqueConstraintHoldersByTable.clear();
	}
