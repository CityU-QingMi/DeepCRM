	@Override
	public Predicate isFalse(Expression<Boolean> expression) {
		if ( CompoundPredicate.class.isInstance( expression ) ) {
			final CompoundPredicate predicate = (CompoundPredicate) expression;
			if ( predicate.getExpressions().size() == 0 ) {
				return new BooleanStaticAssertionPredicate(
						this,
						predicate.getOperator() == Predicate.BooleanOperator.OR
				);
			}
			predicate.not();
			return predicate;
		}
		else if ( Predicate.class.isInstance( expression ) ) {
			final Predicate predicate = (Predicate) expression;
			predicate.not();
			return predicate;
		}
		return new BooleanAssertionPredicate( this, expression, Boolean.FALSE );
	}
