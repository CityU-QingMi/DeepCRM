	@Override
	public <C extends Collection<?>> Expression<Integer> size(Expression<C> exp) {
		if ( LiteralExpression.class.isInstance(exp) ) {
			return size( ( (LiteralExpression<C>) exp ).getLiteral() );
		}
		else if ( PluralAttributePath.class.isInstance(exp) ) {
			return new SizeOfPluralAttributeExpression( this, (PluralAttributePath<C>) exp );
		}
		// TODO : what other specific types?  any?
		throw new IllegalArgumentException("unknown collection expression type [" + exp.getClass().getName() + "]" );
	}
