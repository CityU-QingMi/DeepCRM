	private static String interpretSorting(String sort) {
		if ( StringHelper.isEmpty( sort ) ) {
			return null;
		}

		if ( "unsorted".equals( sort ) ) {
			return null;
		}

		return sort;
	}
