	@SuppressWarnings( {""})
	public <N extends Number> ComparisonPredicate(
			CriteriaBuilderImpl criteriaBuilder,
			ComparisonOperator comparisonOperator,
			Expression<N> leftHandSide,
			Number rightHandSide) {
		super( criteriaBuilder );
		this.comparisonOperator = comparisonOperator;
		this.leftHandSide = leftHandSide;
		Class type = leftHandSide.getJavaType();
		if ( Number.class.equals( type ) ) {
			this.rightHandSide = new LiteralExpression( criteriaBuilder, rightHandSide );
		}
		else {
			N converted = (N) ValueHandlerFactory.convert( rightHandSide, type );
			this.rightHandSide = new LiteralExpression<N>( criteriaBuilder, converted );
		}
	}
