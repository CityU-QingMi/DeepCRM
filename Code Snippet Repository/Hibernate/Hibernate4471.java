	@Override
	public String render(boolean isNegated, RenderingContext renderingContext) {
		final String operator = isNegated ? " not like " : " like ";
		StringBuilder buffer = new StringBuilder();
		buffer.append( ( (Renderable) getMatchExpression() ).render( renderingContext ) )
				.append( operator )
				.append( ( (Renderable) getPattern() ).render( renderingContext ) );
		if ( escapeCharacter != null ) {
			buffer.append( " escape " )
					.append( ( (Renderable) getEscapeCharacter() ).render( renderingContext ) );
		}
		return buffer.toString();
	}
