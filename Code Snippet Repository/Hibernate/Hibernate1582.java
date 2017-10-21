	private int countOccurrences(Object element, List list, Type elementType)
			throws HibernateException {
		final Iterator iter = list.iterator();
		int result = 0;
		while ( iter.hasNext() ) {
			if ( elementType.isSame( element, iter.next() ) ) {
				result++;
			}
		}
		return result;
	}
