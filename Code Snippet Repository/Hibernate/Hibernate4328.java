	@Override
	public <E, C extends Collection<E>> Predicate isMember(E e, Expression<C> collectionExpression) {
		if ( ! PluralAttributePath.class.isInstance( collectionExpression ) ) {
			throw new IllegalArgumentException(
					"unknown collection expression type [" + collectionExpression.getClass().getName() + "]"
			);
		}
		return new MemberOfPredicate<>(
				this,
				e,
				(PluralAttributePath<C>) collectionExpression
		);
	}
