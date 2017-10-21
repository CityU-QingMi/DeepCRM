	@SuppressWarnings("")
	private <X, T, V extends T, K extends JoinImplementor> K treat(
			Join<X, T> join,
			Class<V> type,
			BiFunction<Join<X, T>, Class<V>, K> f) {
		final Set<Join<X, ?>> joins = join.getParent().getJoins();
		final K treatAs = f.apply( join, type );
		joins.add( treatAs );
		return treatAs;
	}
