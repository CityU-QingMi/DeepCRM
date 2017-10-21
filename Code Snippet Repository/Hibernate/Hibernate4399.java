	@Override
	public String render(RenderingContext renderingContext) {
		StringBuilder buffer = new StringBuilder();
		buffer.append( "locate(" )
				.append( ( (Renderable) getPattern() ).render( renderingContext ) )
				.append( ',' )
				.append( ( (Renderable) getString() ).render( renderingContext ) );
		if ( getStart() != null ) {
			buffer.append( ',' )
					.append( ( (Renderable) getStart() ).render( renderingContext ) );
		}
		buffer.append( ')' );
		return buffer.toString();
	}
