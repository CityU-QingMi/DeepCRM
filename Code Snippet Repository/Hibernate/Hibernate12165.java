	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append( "AccessTypeInformation" );
		sb.append( "{fqcn='" ).append( fqcn ).append( '\'' );
		sb.append( ", explicitAccessType=" ).append( explicitAccessType );
		sb.append( ", defaultAccessType=" ).append( defaultAccessType );
		sb.append( '}' );
		return sb.toString();
	}
