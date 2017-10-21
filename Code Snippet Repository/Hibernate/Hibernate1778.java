	@Override
	public String getLimitString(String querySelect, int offset, int limit) {
		final StringBuilder soff = new StringBuilder( " offset " + offset );
		final StringBuilder slim = new StringBuilder( " fetch first " + limit + " rows only" );
		final StringBuilder sb = new StringBuilder( querySelect.length() + soff.length() + slim.length() )
				.append( querySelect );
		if ( offset > 0 ) {
			sb.append( soff );
		}
		if ( limit > 0 ) {
			sb.append( slim );
		}
		return sb.toString();
	}
