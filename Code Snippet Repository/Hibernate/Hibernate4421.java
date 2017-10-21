	@Override
	public <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute, JoinType jt) {
		if ( !canBeJoinSource() ) {
			throw illegalJoin();
		}

		Join<X, Y> join = constructJoin( attribute, jt );
		joinScope.addJoin( join );
		return join;
	}
