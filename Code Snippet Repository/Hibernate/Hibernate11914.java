	@Override
	public String render(Type firstArgumentType, List arguments, SessionFactoryImplementor sessionFactory) {
		final StringBuffer buf = new StringBuffer();
		if ( arguments.size() < 1 ) {
			buf.append( getName() ).append( "()" );
		}
		else {
			buf.append( arguments.get( 0 ) ).append( "." )
					.append( getName() ).append( "(" );
			for ( int i = 1; i < arguments.size(); i++ ) {
				buf.append( arguments.get( i ) );
				if ( i < arguments.size() - 1 ) {
					buf.append( "," );
				}
			}
			buf.append( ")" );
		}
		return buf.toString();
	}
