		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			final String methodName = method.getName();
			if ( "getDatabaseProductName".equals( methodName ) ) {
				return databaseName;
			}

			if ( "getDatabaseMajorVersion".equals( methodName ) ) {
				return Integer.valueOf( majorVersion );
			}

			if ( "getDatabaseMinorVersion".equals( methodName ) ) {
				return Integer.valueOf( minorVersion );
			}

			if ( "getConnection".equals( methodName ) ) {
				return connectionProxy;
			}

			if ( "toString".equals( methodName ) ) {
				return "DatabaseMetaData proxy [db-name=" + databaseName + ", version=" + majorVersion + "]";
			}

			if ( "hashCode".equals( methodName ) ) {
				return Integer.valueOf( this.hashCode() );
			}

			if ( canThrowSQLException( method ) ) {
				throw new SQLException();
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
