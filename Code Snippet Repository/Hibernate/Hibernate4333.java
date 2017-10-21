	@Override
	public Predicate isTrue(Expression<Boolean> expression) {
		if ( CompoundPredicate.class.isInstance( expression ) ) {
			final CompoundPredicate predicate = (CompoundPredicate) expression;
			if ( predicate.getExpressions().size() == 0 ) {
				return new BooleanStaticAssertionPredicate(
						this,
						predicate.getOperator() == Predicate.BooleanOperator.AND
				);
			}
			return predicate;
		}
		else if ( Predicate.class.isInstance( expression ) ) {
			return (Predicate) expression;
		}
		return new BooleanAssertionPredicate( this, expression, Boolean.TRUE );
	}
