	private void processQuerySpaceJoins(QuerySpace querySpace, JoinFragment joinFragment) {
		// IMPL NOTES:
		//
		// 1) The querySpace and the left-hand-side of each of the querySpace's joins should really be the same.
		// validate that?  any cases where they wont be the same?
		//
		// 2) Assume that the table fragments for the left-hand-side have already been rendered.  We just need to
		// figure out the proper lhs table alias to use and the column/formula from the lhs to define the join
		// condition, which can be different per Join

		for ( Join join : querySpace.getJoins() ) {
			processQuerySpaceJoin( join, joinFragment );
		}
	}
