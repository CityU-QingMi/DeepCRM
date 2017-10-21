	@Override
	@SuppressWarnings({ "" })
	public <C extends Collection<?>> Predicate isEmpty(Expression<C> collectionExpression) {
		if ( PluralAttributePath.class.isInstance(collectionExpression) ) {
			return new IsEmptyPredicate( this, (PluralAttributePath<C>) collectionExpression );
		}
		// TODO : what other specific types?  any?
		throw new IllegalArgumentException(
				"unknown collection expression type [" + collectionExpression.getClass().getName() + "]"
		);
	}
