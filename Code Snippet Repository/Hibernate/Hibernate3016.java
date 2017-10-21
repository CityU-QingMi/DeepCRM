	@Override
	public String getAlias(String table) {
		if ( table == null ) {
			return rootAlias;
		}
		else {
			return AbstractEntityPersister.generateTableAlias(
					rootAlias,
					AbstractEntityPersister.getTableId( table, tables )
			);
		}
	}
