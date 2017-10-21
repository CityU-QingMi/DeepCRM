	@Override
	@SuppressWarnings({ "" })
	public <M extends Map<?, ?>> Predicate isMapEmpty(Expression<M> mapExpression) {
		if ( PluralAttributePath.class.isInstance( mapExpression ) ) {
			return new IsEmptyPredicate( this, (PluralAttributePath<M>) mapExpression );
		}
		// TODO : what other specific types?  any?
		throw new IllegalArgumentException(
				"unknown collection expression type [" + mapExpression.getClass().getName() + "]"
		);
	}
