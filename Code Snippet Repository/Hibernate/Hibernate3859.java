	@SuppressWarnings("")
	public boolean equals(Column column) {
		if ( null == column ) {
			return false;
		}
		if ( this == column ) {
			return true;
		}

		return isQuoted() ?
				name.equals( column.name ) :
				name.equalsIgnoreCase( column.name );
	}
