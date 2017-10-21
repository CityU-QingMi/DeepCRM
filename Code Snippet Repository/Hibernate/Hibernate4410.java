	@Override
	public <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map, JoinType jt) {
		if ( !canBeJoinSource() ) {
			throw illegalJoin();
		}

		final MapJoin<X, K, V> join = constructJoin( map, jt );
		joinScope.addJoin( join );
		return join;
	}
