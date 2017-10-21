	@Override
	public String render(Type columnType, List args, SessionFactoryImplementor factory) throws QueryException {
		final boolean threeArgs = args.size() > 2;
		final Object pattern = args.get( 0 );
		final Object string = args.get( 1 );
		final Object start = threeArgs ? args.get( 2 ) : null;

		final StringBuilder buf = new StringBuilder();
		buf.append( "charindex(" ).append( pattern ).append( ", " );
		if (threeArgs) {
			buf.append( "right(" );
		}
		buf.append( string );
		if (threeArgs) {
			buf.append( ", char_length(" ).append( string ).append( ")-(" ).append( start ).append( "-1))" );
		}
		buf.append( ')' );
		return buf.toString();
	}
