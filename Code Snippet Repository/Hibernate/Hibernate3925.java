	@Override
	public void addColumn(Column column) {
		final Iterator<Column> columnIterator = getTable().getColumnIterator();
		while ( columnIterator.hasNext() ) {
			final Column next = columnIterator.next();
			if ( next.getCanonicalName().equals( column.getCanonicalName() ) ) {
				next.setNullable( false );
				log.debugf(
						"Forcing column [%s] to be non-null as it is part of the primary key for table [%s]",
						column.getCanonicalName(),
						getTableNameForLogging( column )
				);
			}
		}
		super.addColumn( column );
	}
