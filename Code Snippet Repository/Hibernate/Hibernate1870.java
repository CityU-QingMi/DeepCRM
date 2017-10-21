	@Override
	public String render(Type firstArgumentType, List arguments, SessionFactoryImplementor factory) {
		final StringBuilder buf = new StringBuilder().append( begin );
		for ( int i = 0; i < arguments.size(); i++ ) {
			buf.append( transformArgument( (String) arguments.get( i ) ) );
			if ( i < arguments.size() - 1 ) {
				buf.append( sep );
			}
		}
		return buf.append( end ).toString();
	}
