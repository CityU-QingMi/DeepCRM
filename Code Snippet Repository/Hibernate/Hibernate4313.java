	@Override
	@SuppressWarnings({ "" })
	public <N extends Number> Expression<N> sum(N n, Expression<? extends N> expression) {
		if ( expression == null || n == null ) {
			throw new IllegalArgumentException( "arguments to sum() cannot be null" );
		}

		final Class resultType = BinaryArithmeticOperation.determineResultType( n.getClass(), expression.getJavaType() );

		return new BinaryArithmeticOperation<N>(
				this,
				resultType,
				BinaryArithmeticOperation.Operation.ADD,
				n,
				expression
		);
	}
