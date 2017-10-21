	@Deprecated
	@SuppressWarnings({ "" })
	public static List<String[]> buildUniqueConstraints(UniqueConstraint[] constraintsArray) {
		List<String[]> result = new ArrayList<String[]>();
		if ( constraintsArray.length != 0 ) {
			for (UniqueConstraint uc : constraintsArray) {
				result.add( uc.columnNames() );
			}
		}
		return result;
	}
