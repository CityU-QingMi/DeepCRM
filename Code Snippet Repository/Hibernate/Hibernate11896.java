	public static String booleanCombination(RelationshipMask[] masks) {
		String strMask = null;
		for ( RelationshipMask relationshipMask : masks ) {
			if ( strMask == null ) {
				strMask = relationshipMask.toString();
			}
			else {
				strMask += "+" + relationshipMask.toString();
			}
		}
		return strMask;
	}
