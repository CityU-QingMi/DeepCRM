	@Override
	public Object extract(Object[] data, ResultSet resultSet, SharedSessionContractImplementor session)
			throws SQLException, HibernateException {
		if ( constructor == null ) {
			throw new IllegalStateException( "Constructor to call was null" );
		}

		final Object[] args = new Object[ scalarProcessors.length ];
		for ( int i = 0; i < scalarProcessors.length; i++ ) {
			args[i] = scalarProcessors[i].extract( data, resultSet, session );
		}

		try {
			return constructor.newInstance( args );
		}
		catch (Exception e) {
			throw new HibernateException(
					String.format( "Unable to call %s constructor", constructor.getDeclaringClass() ),
					e
			);
		}
	}
