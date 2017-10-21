	@Override
	@SuppressWarnings( {""})
	public Expression<Number> quot(Number number, Expression<? extends Number> expression) {
		if ( expression == null || number == null ) {
			throw new IllegalArgumentException( "arguments to quot() cannot be null" );
		}

		final Class resultType = BinaryArithmeticOperation.determineResultType( number.getClass(), expression.getJavaType(), true );

		return new BinaryArithmeticOperation<Number>(
				this,
				resultType,
				BinaryArithmeticOperation.Operation.DIVIDE,
				number,
				expression
		);
	}
