	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		if ( methodName.startsWith( "set" ) ) {
			String propertyName = methodName.substring( 3 );
			data.put( propertyName, args[0] );
		}
		else if ( methodName.startsWith( "get" ) ) {
			String propertyName = methodName.substring( 3 );
			return data.get( propertyName );
		}
		else if ( "toString".equals( methodName ) ) {
			return entityName + "#" + data.get( "Id" );
		}
		else if ( "hashCode".equals( methodName ) ) {
			return new Integer( this.hashCode() );
		}
		return null;
	}
