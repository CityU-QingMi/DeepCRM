	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		final Object[] valueHandlerResult;
		if ( valueHandlers == null ) {
			valueHandlerResult = tuple;
		}
		else {
			valueHandlerResult = new Object[tuple.length];
			for ( int i = 0; i < tuple.length; i++ ) {
				ValueHandlerFactory.ValueHandler valueHandler = valueHandlers.get( i );
				valueHandlerResult[i] = valueHandler == null
						? tuple[i]
						: valueHandler.convert( tuple[i] );
			}
		}

		return tupleElements == null
				? valueHandlerResult.length == 1 ? valueHandlerResult[0] : valueHandlerResult
				: new TupleImpl( tuple );

	}
