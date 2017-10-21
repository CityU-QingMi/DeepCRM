	@Override
	public String render(RenderingContext renderingContext) {
		String renderedTrimChar;
		if ( trimCharacter.getClass().isAssignableFrom( 
				LiteralExpression.class ) ) {
			// If the character is a literal, treat it as one.  A few dialects
			// do not support parameters as trim() arguments.
			renderedTrimChar = '\'' + ( (LiteralExpression<Character>)
					trimCharacter ).getLiteral().toString() + '\'';
		}
		else {
			renderedTrimChar = ( (Renderable) trimCharacter ).render( 
					renderingContext );
		}
		return new StringBuilder()
				.append( "trim(" )
				.append( trimspec.name() )
				.append( ' ' )
				.append( renderedTrimChar )
				.append( " from " )
				.append( ( (Renderable) trimSource ).render( renderingContext ) )
				.append( ')' )
				.toString();
	}
