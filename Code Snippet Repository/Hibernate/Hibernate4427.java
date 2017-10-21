	@Override
	public <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list, JoinType jt) {
		if ( !canBeJoinSource() ) {
			throw illegalJoin();
		}

		final ListJoin<X, Y> join = constructJoin( list, jt );
		joinScope.addJoin( join );
		return join;
	}
