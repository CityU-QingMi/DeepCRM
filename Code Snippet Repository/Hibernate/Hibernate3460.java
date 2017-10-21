	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		if ( tuple.length != tupleElements.size() ) {
			throw new IllegalArgumentException(
					"Size mismatch between tuple result [" + tuple.length + "] and expected tuple elements [" +
							tupleElements.size() + "]"
			);
		}
		return new HqlTupleImpl( tuple );
	}
