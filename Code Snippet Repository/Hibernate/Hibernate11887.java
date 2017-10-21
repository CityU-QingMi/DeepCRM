	public String render(Type firstArgtype, List args, SessionFactoryImplementor factory)
			throws QueryException {
		final StringBuffer buf = new StringBuffer();
		if ( args.isEmpty() ) {
			throw new QueryException(
					"First Argument in arglist must be object of which property is queried"
			);
		}
		buf.append( args.get( 0 ) ).append( "." ).append( name );
		return buf.toString();
	}
