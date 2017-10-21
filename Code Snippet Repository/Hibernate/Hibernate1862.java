	@Override
	public String render(Type firstArgumentType, List args, SessionFactoryImplementor factory) throws QueryException {
		final boolean threeArgs = args.size() > 2;
		final Object pattern = args.get( 0 );
		final Object string = args.get( 1 );
		final Object start = threeArgs ? args.get( 2 ) : null;

		final StringBuilder buf = new StringBuilder();
		if (threeArgs) {
			buf.append( '(' );
		}
		buf.append( "position(" ).append( pattern ).append( " in " );
		if (threeArgs) {
			buf.append( "substring(");
		}
		buf.append( string );
		if (threeArgs) {
			buf.append( ", " ).append( start ).append( ')' );
		}
		buf.append( ')' );
		if (threeArgs) {
			buf.append( '+' ).append( start ).append( "-1)" );
		}
		return buf.toString();
	}
