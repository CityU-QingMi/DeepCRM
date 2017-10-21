	@Override
	public int determineTableNumberForColumn(String columnName) {
		// HHH-7630: In case the naturalOrder/identifier column is explicitly given in the ordering, check here.
		for ( int i = 0, max = naturalOrderTableKeyColumns.length; i < max; i++ ) {
			final String[] keyColumns = naturalOrderTableKeyColumns[i];
			if ( ArrayHelper.contains( keyColumns, columnName ) ) {
				return naturalOrderPropertyTableNumbers[i];
			}
		}
		
		final String[] subclassColumnNameClosure = getSubclassColumnClosure();
		for ( int i = 0, max = subclassColumnNameClosure.length; i < max; i++ ) {
			final boolean quoted = subclassColumnNameClosure[i].startsWith( "\"" )
					&& subclassColumnNameClosure[i].endsWith( "\"" );
			if ( quoted ) {
				if ( subclassColumnNameClosure[i].equals( columnName ) ) {
					return getSubclassColumnTableNumberClosure()[i];
				}
			}
			else {
				if ( subclassColumnNameClosure[i].equalsIgnoreCase( columnName ) ) {
					return getSubclassColumnTableNumberClosure()[i];
				}
			}
		}
		throw new HibernateException( "Could not locate table which owns column [" + columnName + "] referenced in order-by mapping" );
	}
