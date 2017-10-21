	public int getJoinNumber(Property prop) {
		int result = 1;
		Iterator iter = getSubclassJoinClosureIterator();
		while ( iter.hasNext() ) {
			Join join = (Join) iter.next();
			if ( join.containsProperty( prop ) ) {
				return result;
			}
			result++;
		}
		return 0;
	}
