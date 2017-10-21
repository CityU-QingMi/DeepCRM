	public String render(RenderingContext renderingContext) {
		StringBuilder buffer = new StringBuilder();
		buffer.append( "substring(" )
				.append( ( (Renderable) getValue() ).render( renderingContext ) )
				.append( ',' )
				.append( ( (Renderable) getStart() ).render( renderingContext ) );
		if ( getLength() != null ) {
			buffer.append( ',' )
					.append( ( (Renderable) getLength() ).render( renderingContext ) );
		}
		buffer.append( ')' );
		return buffer.toString();
	}
