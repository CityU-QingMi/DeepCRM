	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) return false;
		if( !( obj instanceof WidgetId ) ) return false;
		
		WidgetId id = ( WidgetId )obj;
		if( this.getCode( ) == null || id.getCode( ) == null || this.getDivision( ) == null || id.getDivision( ) == null ) return false;
		
		return this.toString( ).equals( id.toString( ) );
	}
