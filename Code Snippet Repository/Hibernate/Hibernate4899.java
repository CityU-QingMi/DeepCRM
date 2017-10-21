	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		try {
			return constructor.newInstance( tuple );
		}
		catch ( Exception e ) {
			throw new QueryException( 
					"could not instantiate class [" + constructor.getDeclaringClass().getName() + "] from tuple",
					e
			);
		}
	}
