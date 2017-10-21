	@Override
	public Type getDataType() {
		Type type = super.getDataType();
		if ( type != null ) {
			return type;
		}
		FromElement fe = getFromElement();
		if ( fe != null ) {
			return fe.getDataType();
		}
		SQLFunction sf = getWalker().getSessionFactoryHelper().findSQLFunction( getText() );
		if ( sf != null ) {
			return sf.getReturnType( null, getWalker().getSessionFactoryHelper().getFactory() );
		}
		return null;
	}
