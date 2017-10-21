	@Override
	public String render(Type firstArgumentType, List arguments, SessionFactoryImplementor sessionFactory) {
		final boolean hasArgs = !arguments.isEmpty();
		final StringBuilder buf = new StringBuilder( getName() );
		if ( hasArgs ) {
			buf.append( "(" );
			for ( int i = 0; i < arguments.size(); i++ ) {
				buf.append( arguments.get( i ) );
				if ( i < arguments.size() - 1 ) {
					buf.append( ", " );
				}
			}
			buf.append( ")" );
		}
		return buf.toString();
	}
