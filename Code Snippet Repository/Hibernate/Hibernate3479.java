	protected static String orderBy(List associations)
			throws MappingException {
		StringBuilder buf = new StringBuilder();
		Iterator iter = associations.iterator();
		OuterJoinableAssociation last = null;
		while ( iter.hasNext() ) {
			OuterJoinableAssociation oj = (OuterJoinableAssociation) iter.next();
			if ( oj.getJoinType() == JoinType.LEFT_OUTER_JOIN ) { // why does this matter?
				if ( oj.getJoinable().isCollection() ) {
					final QueryableCollection queryableCollection = (QueryableCollection) oj.getJoinable();
					if ( queryableCollection.hasOrdering() ) {
						final String orderByString = queryableCollection.getSQLOrderByString( oj.getRHSAlias() );
						buf.append( orderByString ).append( ", " );
					}
				}
				else {
					// it might still need to apply a collection ordering based on a
					// many-to-many defined order-by...
					if ( last != null && last.getJoinable().isCollection() ) {
						final QueryableCollection queryableCollection = (QueryableCollection) last.getJoinable();
						if ( queryableCollection.isManyToMany() && last.isManyToManyWith( oj ) ) {
							if ( queryableCollection.hasManyToManyOrdering() ) {
								final String orderByString = queryableCollection.getManyToManyOrderByString( oj.getRHSAlias() );
								buf.append( orderByString ).append( ", " );
							}
						}
					}
				}
			}
			last = oj;
		}
		if ( buf.length() > 0 ) {
			buf.setLength( buf.length() - 2 );
		}
		return buf.toString();
	}
