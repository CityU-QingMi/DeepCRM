	@Override
	public String render(Type firstArgumentType, List arguments, SessionFactoryImplementor sessionFactory) {
		final StringBuilder buf = new StringBuilder();
		buf.append( getRenderedName( arguments) ).append( '(' );
		for ( int i = 0; i < arguments.size(); i++ ) {
			buf.append( arguments.get( i ) );
			if ( i < arguments.size() - 1 ) {
				buf.append( ", " );
			}
		}
		return buf.append( ')' ).toString();
	}
