	@Override
	public String render(Type firstArgumentType, List arguments, SessionFactoryImplementor sessionFactory) {
		if ( arguments.size() == 0 ) {
			return getName() + "()";
		}
		else {
			final StringBuilder buf = new StringBuilder();
			buf.append( arguments.get( 0 ) ).append( "." ).append( getName() ).append( '(' );
			for ( int i = 1; i < arguments.size(); i++ ) {
				final Object argument = arguments.get( i );
				final boolean parseFromWKB = this.firstArgumentIsGeometryType && i == 1 && "?".equals( argument );
				if ( parseFromWKB ) {
					buf.append( "ST_GeomFromEWKB(" );
				}
				buf.append( argument );
				if ( parseFromWKB ) {
					buf.append( ")" );
				}
				if ( i < arguments.size() - 1 ) {
					buf.append( ", " );
				}
			}
			buf.append( ')' );
			return buf.toString();
		}
	}
