	private List<String> getFollowers(Iterator parentPropIter, String reducedName, String name) {
		boolean hasFollowers = false;
		List<String> followers = new ArrayList<String>();
		while ( parentPropIter.hasNext() ) {
			String currentPropertyName = ( (Property) parentPropIter.next() ).getName();
			String currentName = reducedName + '.' + currentPropertyName;
			if ( hasFollowers ) {
				followers.add( currentName );
			}
			if ( name.equals( currentName ) ) {
				hasFollowers = true;
			}
		}
		return followers;
	}
