	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) return false;
		if( !( obj instanceof HelperId ) ) return false;
		
		HelperId id = ( HelperId )obj;
		if( this.getName() == null || id.getName() == null || this.getType() == null || id.getType() == null ) return false;
		
		return this.toString( ).equals( id.toString( ) );
	}
