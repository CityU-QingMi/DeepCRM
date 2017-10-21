	public Type aggregateType(List funcTokenList, Type type, QueryTranslatorImpl q) throws QueryException {
		Type retType = type;
		Type argType;
		for ( int i = funcTokenList.size() - 1; i >= 0; i-- ) {
			argType = retType;
			String funcToken = ( String ) funcTokenList.get( i );
			retType = getFunction( funcToken, q ).getReturnType( argType, q.getFactory() );
		}
		return retType;
	}
