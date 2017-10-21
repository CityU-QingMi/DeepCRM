	@Override
	public String render(RenderingContext renderingContext) {
		StringBuilder buffer = new StringBuilder();
		if ( isStandardJpaFunction() ) {
			buffer.append( getFunctionName() )
					.append( "(" );
		}
		else {
			buffer.append( "function('" )
					.append( getFunctionName() )
					.append( "', " );
		}
		renderArguments( buffer, renderingContext );
		buffer.append( ')' );
		return buffer.toString();
	}
