	@Override
	@SuppressWarnings({ "" })
	public <N extends Number> Expression<N> prod(N n, Expression<? extends N> expression) {
		if ( n == null || expression == null ) {
			throw new IllegalArgumentException( "arguments to prod() cannot be null" );
		}

		final Class resultType = BinaryArithmeticOperation.determineResultType( n.getClass(), expression.getJavaType() );

		return (BinaryArithmeticOperation<N>) new BinaryArithmeticOperation(
				this,
				resultType,
				BinaryArithmeticOperation.Operation.MULTIPLY,
				n,
				expression
		);
	}
