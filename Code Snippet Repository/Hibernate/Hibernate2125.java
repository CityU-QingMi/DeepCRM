	private Method getResultSetByNameMethod() {
		if ( getResultSetByNameMethod == null ) {
			try {
				getResultSetByNameMethod = CallableStatement.class.getMethod( "getObject", String.class, Class.class );
			}
			catch (NoSuchMethodException e) {
				throw new HibernateException( "CallableStatement class does not define getObject(String,Class) method" );
			}
			catch (Exception e) {
				throw new HibernateException( "Unexpected error trying to access CallableStatement#getObject(String,Class)" );
			}
		}
		return getResultSetByNameMethod;
	}
