	@Override
	public Expression<Integer> mod(Integer integer, Expression<Integer> expression) {
		if ( integer == null || expression == null ) {
			throw new IllegalArgumentException( "arguments to mod() cannot be null" );
		}

		return new BinaryArithmeticOperation<Integer>(
				this,
				Integer.class,
				BinaryArithmeticOperation.Operation.MOD,
				integer,
				expression
		);
	}
