	@Deprecated
	public static Number createNumber(long value, Class clazz) throws IdentifierGenerationException {
		if ( clazz == Long.class ) {
			return value;
		}
		else if ( clazz == Integer.class ) {
			return (int) value;
		}
		else if ( clazz == Short.class ) {
			return (short) value;
		}
		else {
			throw new IdentifierGenerationException( "unrecognized id type : " + clazz.getName() );
		}
	}
