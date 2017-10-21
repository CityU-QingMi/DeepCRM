	public String render(Type firstArgumentType, List args, SessionFactoryImplementor factory) throws QueryException {
		final StringBuffer buf = new StringBuffer();
		if ( args.isEmpty() ) {
			throw new QueryException(
					"First Argument in arglist must be object to which method is applied"
			);
		}
		buf.append( args.get( 0 ) ).append( "." ).append( name ).append( '(' );
		for ( int i = 1; i < args.size(); i++ ) {
			buf.append( args.get( i ) );
			if ( i < args.size() - 1 ) {
				buf.append( ", " );
			}
		}
		return buf.append( ')' ).toString();
	}
