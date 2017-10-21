	public String selectFragment(
			Joinable rhs,
			String rhsAlias,
			String lhsAlias,
			String entitySuffix,
			String collectionSuffix,
			boolean includeCollectionColumns) {
		StringBuilder buf = new StringBuilder();
		if ( includeCollectionColumns ) {
//			buf.append( selectFragment( lhsAlias, "" ) )//ignore suffix for collection columns!
			buf.append( selectFragment( lhsAlias, collectionSuffix ) )
					.append( ", " );
		}
		OuterJoinLoadable ojl = (OuterJoinLoadable) getElementPersister();
		return buf.append( ojl.selectFragment( lhsAlias, entitySuffix ) )//use suffix for the entity columns
				.toString();
	}
