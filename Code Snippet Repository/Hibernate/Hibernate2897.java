	public static BigInteger extractBigInteger(IntegralDataTypeHolder holder) {
		if ( holder.getClass() == BasicHolder.class ) {
			( (BasicHolder) holder ).checkInitialized();
			return BigInteger.valueOf( ( (BasicHolder) holder ).value );
		}
		else if ( holder.getClass() == BigIntegerHolder.class ) {
			( (BigIntegerHolder) holder ).checkInitialized();
			return ( (BigIntegerHolder) holder ).value;
		}
		else if ( holder.getClass() == BigDecimalHolder.class ) {
			( (BigDecimalHolder) holder ).checkInitialized();
			// scale should already be set...
			return ( (BigDecimalHolder) holder ).value.toBigInteger();
		}
		throw new IdentifierGenerationException( "Unknown IntegralDataTypeHolder impl [" + holder + "]" );
	}
