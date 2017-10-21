	protected final JoinFragment mergeOuterJoins(List associations)
			throws MappingException {
		JoinFragment outerjoin = getDialect().createOuterJoinFragment();
		Iterator iter = associations.iterator();
		OuterJoinableAssociation last = null;
		while ( iter.hasNext() ) {
			final OuterJoinableAssociation oj = (OuterJoinableAssociation) iter.next();
			if ( last != null && last.isManyToManyWith( oj ) ) {
				oj.addManyToManyJoin( outerjoin, (QueryableCollection) last.getJoinable() );
			}
			else {
				oj.addJoins( outerjoin );
			}
			last = oj;
		}
		last = null;
		return outerjoin;
	}
