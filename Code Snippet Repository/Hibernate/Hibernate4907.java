	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		if ( aliases != null && aliases.length != tupleLength ) {
			throw new IllegalStateException(
					"aliases expected length is " + tupleLength +
					"; actual length is " + aliases.length );
		}
		// really more correct to pass index( aliases.getClass(), aliases )
		// as the 2nd arg to the following statement;
		// passing null instead because it ends up being ignored.
		return ACTUAL_TRANSFORMER.transformTuple( index( tuple.getClass(), tuple ), null );
	}
