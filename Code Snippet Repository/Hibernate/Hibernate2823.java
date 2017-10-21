	private void addJoin(JoinSequence joinSequence, QueryTranslatorImpl q) throws QueryException {
		//JoinFragment fromClause = q.createJoinFragment(true);
		//fromClause.addJoins( join.toJoinFragment().toFromFragmentString(), StringHelper.EMPTY_STRING );
		q.addFromJoinOnly( pathExpressionParser.getName(), joinSequence );
		try {
			addToCurrentJoin( joinSequence.toJoinFragment( q.getEnabledFilters(), true ).toWhereFragmentString() );
		}
		catch ( MappingException me ) {
			throw new QueryException( me );
		}
	}
