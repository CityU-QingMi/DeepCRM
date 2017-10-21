	public String sqlConstraintString(Dialect dialect) {
		StringBuilder buf = new StringBuilder("primary key (");
		Iterator iter = getColumnIterator();
		while ( iter.hasNext() ) {
			buf.append( ( (Column) iter.next() ).getQuotedName(dialect) );
			if ( iter.hasNext() ) {
				buf.append(", ");
			}
		}
		return buf.append(')').toString();
	}
