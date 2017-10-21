	private void addJoinNodes(QueryNode query, JoinSequence join, FromElement fromElement) {
		JoinFragment joinFragment = join.toJoinFragment(
				walker.getEnabledFilters(),
				fromElement.useFromFragment() || fromElement.isDereferencedBySuperclassOrSubclassProperty(),
				fromElement.getWithClauseFragment()
		);

		String frag = joinFragment.toFromFragmentString();
		String whereFrag = joinFragment.toWhereFragmentString();

		// If the from element represents a JOIN_FRAGMENT and it is
		// a theta-style join, convert its type from JOIN_FRAGMENT
		// to FROM_FRAGMENT
		if ( fromElement.getType() == JOIN_FRAGMENT &&
				( join.isThetaStyle() || StringHelper.isNotEmpty( whereFrag ) ) ) {
			fromElement.setType( FROM_FRAGMENT );
			fromElement.getJoinSequence().setUseThetaStyle( true ); // this is used during SqlGenerator processing
		}

		// If there is a FROM fragment and the FROM element is an explicit, then add the from part.
		if ( fromElement.useFromFragment() ||
				( fromElement.getFromClause().isSubQuery()
						&& fromElement.isDereferencedBySuperclassOrSubclassProperty() ) /*&& StringHelper.isNotEmpty( frag )*/ ) {
			String fromFragment = processFromFragment( frag, join ).trim();
			LOG.debugf( "Using FROM fragment [%s]", fromFragment );
			processDynamicFilterParameters(
					fromFragment,
					fromElement,
					walker
			);
		}

		syntheticAndFactory.addWhereFragment(
				joinFragment,
				whereFrag,
				query,
				fromElement,
				walker
		);
	}
