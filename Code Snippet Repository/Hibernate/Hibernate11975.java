	private void initGeoDB(Connection conn) {
		String errorMsg = "Problem initializing GeoDB.";
		try {
			Class<?> geoDB = Thread.currentThread().getContextClassLoader().loadClass( "geodb.GeoDB" );
			Method m = geoDB.getDeclaredMethod( "InitGeoDB", new Class[] { Connection.class } );
			m.invoke( null, conn );
		}
		catch ( ClassNotFoundException e ) {
			throw new RuntimeException( errorMsg, e );
		}
		catch ( NoSuchMethodException e ) {
			throw new RuntimeException( errorMsg, e );
		}
		catch ( InvocationTargetException e ) {
			throw new RuntimeException( errorMsg, e );
		}
		catch ( IllegalAccessException e ) {
			throw new RuntimeException( errorMsg, e );
		}
	}
