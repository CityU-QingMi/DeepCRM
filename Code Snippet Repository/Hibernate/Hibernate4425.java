	@Override
	public <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set, JoinType jt) {
		if ( !canBeJoinSource() ) {
			throw illegalJoin();
		}

		final SetJoin<X, Y> join = constructJoin( set, jt );
		joinScope.addJoin( join );
		return join;
	}
