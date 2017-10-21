	private Method getResultSetByPositionMethod() {
		if ( getResultSetByPositionMethod == null ) {
			try {
				getResultSetByPositionMethod = CallableStatement.class.getMethod( "getObject", int.class, Class.class );
			}
			catch (NoSuchMethodException e) {
				throw new HibernateException( "CallableStatement class does not define getObject(int,Class) method" );
			}
			catch (Exception e) {
				throw new HibernateException( "Unexpected error trying to access CallableStatement#getObject(int,Class)" );
			}
		}
		return getResultSetByPositionMethod;
	}
