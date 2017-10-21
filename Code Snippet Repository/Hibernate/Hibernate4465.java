	public InPredicate(
			CriteriaBuilderImpl criteriaBuilder,
			Expression<? extends T> expression,
			Collection<T> values) {
		super( criteriaBuilder );
		this.expression = expression;
		this.values = new ArrayList<Expression<? extends T>>( values.size() );
		final Class<? extends T> javaType = expression.getJavaType();
		ValueHandlerFactory.ValueHandler<? extends T> valueHandler = javaType != null && ValueHandlerFactory.isNumeric(javaType)
				? ValueHandlerFactory.determineAppropriateHandler((Class<? extends T>) javaType)
				: new ValueHandlerFactory.NoOpValueHandler<T>();
		for ( T value : values ) {
			this.values.add(
					new LiteralExpression<T>( criteriaBuilder, valueHandler.convert( value ) )
			);
		}
	}
