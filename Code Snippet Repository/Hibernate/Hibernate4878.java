	protected void applyTableCheck(Table table, StringBuilder buf) {
		if ( dialect.supportsTableCheck() ) {
			final Iterator<String> checkConstraints = table.getCheckConstraintsIterator();
			while ( checkConstraints.hasNext() ) {
				buf.append( ", check (" )
						.append( checkConstraints.next() )
						.append( ')' );
			}
		}
	}
