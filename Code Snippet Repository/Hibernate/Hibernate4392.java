	@SuppressWarnings({ "" })
	public String render(RenderingContext renderingContext) {

		LiteralHandlingMode literalHandlingMode = renderingContext.getCriteriaLiteralHandlingMode();

		switch ( literalHandlingMode ) {
			case AUTO:
				if ( ValueHandlerFactory.isNumeric( literal ) ) {
					return ValueHandlerFactory.determineAppropriateHandler( (Class) literal.getClass() ).render( literal );
				}
				else {
					return bindLiteral( renderingContext );
				}
			case BIND:
				return bindLiteral( renderingContext );
			case INLINE:
				Object literalValue = literal;
				if ( String.class.equals( literal.getClass() ) ) {
					literalValue = renderingContext.getDialect().inlineLiteral( (String) literal );
				}
				return ValueHandlerFactory.determineAppropriateHandler( (Class) literal.getClass() ).render( literalValue );
			default:
				throw new IllegalArgumentException( "Unexpected LiteralHandlingMode: " + literalHandlingMode );
		}
	}
