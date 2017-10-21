	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder( 500 )
				.append( "SessionImpl(" );
		if ( !isClosed() ) {
			buf.append( persistenceContext )
					.append( ";" )
					.append( actionQueue );
		}
		else {
			buf.append( "<closed>" );
		}
		return buf.append( ')' ).toString();
	}
