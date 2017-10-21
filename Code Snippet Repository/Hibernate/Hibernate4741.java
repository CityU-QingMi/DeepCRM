	public boolean matches(ForeignKey fk) {
		if ( refTable.equalsIgnoreCase( fk.getReferencedTable().getName() ) ) {
			if ( fk.getColumnSpan() == references.size() ) {
				List fkRefs;
				if ( fk.isReferenceToPrimaryKey() ) {
					fkRefs = fk.getReferencedTable().getPrimaryKey().getColumns();
				}
				else {
					fkRefs = fk.getReferencedColumns();
				}
				for ( int i = 0; i < fk.getColumnSpan(); i++ ) {
					Column column = fk.getColumn( i );
					Column ref = ( Column ) fkRefs.get( i );
					if ( !hasReference( column, ref ) ) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
