	public Table getTable() {
		if ( table != null ){
			return table;
		}

		if ( isSecondary() ) {
			return getJoin().getTable();
		}
		else {
			return propertyHolder.getTable();
		}
	}
