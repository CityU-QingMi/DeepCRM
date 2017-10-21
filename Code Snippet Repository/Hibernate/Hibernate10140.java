	public static boolean iteratorsContentEqual(Iterator iter1, Iterator iter2) {
		while ( iter1.hasNext() && iter2.hasNext() ) {
			if ( !iter1.next().equals( iter2.next() ) ) {
				return false;
			}
		}

		//noinspection RedundantIfStatement
		if ( iter1.hasNext() || iter2.hasNext() ) {
			return false;
		}

		return true;
	}
