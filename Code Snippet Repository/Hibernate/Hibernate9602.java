	@SuppressWarnings({ "" })
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if ( value == null ) {
			Assert.assertEquals( "Expecting setNull call", "setNull", method.getName() );
			return null;
		}
		if ( method.getName().equals( methodName ) && args.length >= 1 ) {
			checkValue( (T) args[1] );
			return null;
		}
		throw new UnsupportedOperationException( "Unexpected call PreparedStatement." + method.getName() );
	}
