		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			final String methodName = method.getName();
			if ( "getMetaData".equals( methodName ) ) {
				return getMetadataProxy( (Connection) proxy );
			}

			if ( "toString".equals( methodName ) ) {
				return "Connection proxy [@" + hashCode() + "]";
			}

			if ( "hashCode".equals( methodName ) ) {
				return Integer.valueOf( this.hashCode() );
			}

			if ( "getCatalog".equals( methodName ) ) {
				return options.catalogName;
			}

			if ( "supportsRefCursors".equals( methodName ) ) {
				return options.supportsRefCursors;
			}

			if ( canThrowSQLException( method ) ) {
				throw new SQLException();
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
