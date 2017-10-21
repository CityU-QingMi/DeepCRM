	@Override
	@SuppressWarnings({ "" })
	public <N extends Number> Expression<N> prod(Expression<? extends N> expression, N n) {
		if ( expression == null || n == null ) {
			throw new IllegalArgumentException( "arguments to prod() cannot be null" );
		}

		final Class resultType = BinaryArithmeticOperation.determineResultType( expression.getJavaType(), n.getClass() );

		return new BinaryArithmeticOperation<N>(
				this,
				resultType,
				BinaryArithmeticOperation.Operation.MULTIPLY,
				expression,
				n
		);
	}
