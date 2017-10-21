	protected void checkColumnDuplication(Set distinctColumns, Iterator columns)
			throws MappingException {
		while ( columns.hasNext() ) {
			Selectable columnOrFormula = (Selectable) columns.next();
			if ( !columnOrFormula.isFormula() ) {
				Column col = (Column) columnOrFormula;
				if ( !distinctColumns.add( col.getName() ) ) {
					throw new MappingException(
							"Repeated column in mapping for entity: " +
									getEntityName() +
									" column: " +
									col.getName() +
									" (should be mapped with insert=\"false\" update=\"false\")"
					);
				}
			}
		}
	}
