	private static String buildOrderByClauseFromHql(String orderByFragment, PersistentClass associatedClass, String role) {
		if ( orderByFragment != null ) {
			if ( orderByFragment.length() == 0 ) {
				//order by id
				return "id asc";
			}
			else if ( "desc".equals( orderByFragment ) ) {
				return "id desc";
			}
		}
		return orderByFragment;
	}
