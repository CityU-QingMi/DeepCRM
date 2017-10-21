	@Override
	public String toString( ) {
		StringBuffer buf = new StringBuffer( "[id:" );
		buf.append( ( this.getCode( ) == null ) ? "null" : this.getCode( ).toString( ) );
		buf.append( ";code:" );
		buf.append( ( this.getDivision( ) == null ) ? "null" : this.getDivision( ) );
		buf.append( "]" );
		
		return buf.toString( );
	}
