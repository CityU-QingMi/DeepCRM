	@Override
	@SuppressWarnings( {""})
	public Expression<Number> quot(Expression<? extends Number> expression, Number number) {
		if ( expression == null || number == null ) {
			throw new IllegalArgumentException( "arguments to quot() cannot be null" );
		}

		final Class resultType = BinaryArithmeticOperation.determineResultType( expression.getJavaType(), number.getClass(), true );

		return new BinaryArithmeticOperation<Number>(
				this,
				resultType,
				BinaryArithmeticOperation.Operation.DIVIDE,
				expression,
				number
		);
	}
