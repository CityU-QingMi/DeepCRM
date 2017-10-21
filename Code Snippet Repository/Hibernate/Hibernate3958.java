	public void addColumn(Column column) {
		Column old = getColumn( column );
		if ( old == null ) {
			if ( primaryKey != null ) {
				for ( Column c : primaryKey.getColumns() ) {
					if ( c.getCanonicalName().equals( column.getCanonicalName() ) ) {
						column.setNullable( false );
						log.debugf(
								"Forcing column [%s] to be non-null as it is part of the primary key for table [%s]",
								column.getCanonicalName(),
								getNameIdentifier().getCanonicalName()
						);
					}
				}
			}
			this.columns.put( column.getCanonicalName(), column );
			column.uniqueInteger = this.columns.size();
		}
		else {
			column.uniqueInteger = old.uniqueInteger;
		}
	}
