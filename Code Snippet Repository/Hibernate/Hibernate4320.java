	@Override
	public Expression<Integer> mod(Expression<Integer> expression, Integer integer) {
		if ( expression == null || integer == null ) {
			throw new IllegalArgumentException( "arguments to mod() cannot be null" );
		}

		return new BinaryArithmeticOperation<Integer>(
				this,
				Integer.class,
				BinaryArithmeticOperation.Operation.MOD,
				expression,
				integer
		);
	}
