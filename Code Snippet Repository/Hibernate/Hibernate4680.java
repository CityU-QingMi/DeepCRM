	public void addSelectFragmentString(String fragment) {
		if ( fragment.length() > 0 && fragment.charAt( 0 ) == ',' ) {
			fragment = fragment.substring( 1 );
		}
		fragment = fragment.trim();
		if ( fragment.length() > 0 ) {
			if ( select.length() > 0 ) {
				select.append( ", " );
			}
			select.append( fragment );
		}
	}
