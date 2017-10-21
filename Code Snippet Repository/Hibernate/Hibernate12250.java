	private static String buildErrorString(String baseError, Class<?> clazz) {
		StringBuilder builder = new StringBuilder();
		builder.append( baseError );
		builder.append( ".\n\n" );
		builder.append( "Source code for " );
		builder.append( clazz.getName() );
		builder.append( "_.java:" );
		builder.append( "\n" );
		builder.append( getMetaModelSourceAsString( clazz ) );
		return builder.toString();
	}
