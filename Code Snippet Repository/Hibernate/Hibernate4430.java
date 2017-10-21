	@Override
	public JoinImplementor<Z, X> on(Predicate... restrictions) {
		// no matter what, a call to this method replaces any previously set values...
		this.suppliedJoinCondition = null;

		if ( restrictions != null && restrictions.length > 0 ) {
			this.suppliedJoinCondition = criteriaBuilder().and( restrictions );
		}

		return this;
	}
