	@Override
	@SuppressWarnings({ "" })
	public <N extends Number> Expression<N> diff(N n, Expression<? extends N> expression) {
		if ( n == null || expression == null ) {
			throw new IllegalArgumentException( "arguments to diff() cannot be null" );
		}

		final Class resultType = BinaryArithmeticOperation.determineResultType( n.getClass(), expression.getJavaType() );

		return new BinaryArithmeticOperation<N>(
				this,
				resultType,
				BinaryArithmeticOperation.Operation.SUBTRACT,
				n,
				expression
		);
	}
