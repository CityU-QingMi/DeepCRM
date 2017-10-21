	@Override
	public String toString( ) {
		StringBuilder buf = new StringBuilder( "[id:" );
		buf.append( ( this.getName() == null ) ? "null" : this.getName( ) );
		buf.append( ";type:" );
		buf.append( ( this.getType() == null ) ? "null" : this.getType() );
		buf.append( "]" );
		
		return buf.toString( );
	}
