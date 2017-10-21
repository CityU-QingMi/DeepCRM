	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append( "Ejb3JoinColumn" );
		sb.append( "{logicalColumnName='" ).append( getLogicalColumnName() ).append( '\'' );
		sb.append( ", referencedColumn='" ).append( referencedColumn ).append( '\'' );
		sb.append( ", mappedBy='" ).append( mappedBy ).append( '\'' );
		sb.append( '}' );
		return sb.toString();
	}
