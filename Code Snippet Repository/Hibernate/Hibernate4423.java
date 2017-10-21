	@Override
	public <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection, JoinType jt) {
		if ( !canBeJoinSource() ) {
			throw illegalJoin();
		}

		final CollectionJoin<X, Y> join = constructJoin( collection, jt );
		joinScope.addJoin( join );
		return join;
	}
