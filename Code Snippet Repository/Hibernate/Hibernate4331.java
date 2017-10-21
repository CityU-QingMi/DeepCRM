	@Override
	public <M extends Map<?, ?>> Expression<Integer> mapSize(Expression<M> mapExpression) {
		if ( LiteralExpression.class.isInstance( mapExpression ) ) {
			return mapSize( ( (LiteralExpression<M>) mapExpression ).getLiteral() );
		}
		else if ( PluralAttributePath.class.isInstance( mapExpression ) ) {
			return new SizeOfPluralAttributeExpression( this, (PluralAttributePath) mapExpression );
		}
		// TODO : what other specific types?  any?
		throw new IllegalArgumentException("unknown collection expression type [" + mapExpression.getClass().getName() + "]" );
	}
