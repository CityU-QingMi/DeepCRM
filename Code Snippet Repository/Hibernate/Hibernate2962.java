	protected final Object getFinal(int col, Type returnType) throws HibernateException {
		if ( closed ) {
			throw new IllegalStateException( "ScrollableResults is closed" );
		}

		if ( holderInstantiator != null ) {
			throw new HibernateException( "query specifies a holder class" );
		}

		if ( returnType.getReturnedClass() == types[col].getReturnedClass() ) {
			return get( col );
		}
		else {
			return throwInvalidColumnTypeException( col, types[col], returnType );
		}
	}
