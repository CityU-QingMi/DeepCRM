	public static void addColumnsOrFormulas(Element element, Iterator columnIterator) {
		while ( columnIterator.hasNext() ) {
			final Object o = columnIterator.next();
			if ( o instanceof Column ) {
				addColumn( element, (Column) o );
			}
			else if ( o instanceof Formula ) {
				addFormula( element, (Formula) o );
			}
		}
	}
