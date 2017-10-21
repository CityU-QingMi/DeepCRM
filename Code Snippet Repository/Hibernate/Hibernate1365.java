	private static String adjustUserSuppliedValueCollectionOrderingFragment(String orderByFragment) {
		if ( orderByFragment != null ) {
			// NOTE: "$element$" is a specially recognized collection property recognized by the collection persister
			if ( orderByFragment.length() == 0 ) {
				//order by element
				return "$element$ asc";
			}
			else if ( "desc".equals( orderByFragment ) ) {
				return "$element$ desc";
			}
		}
		return orderByFragment;
	}
