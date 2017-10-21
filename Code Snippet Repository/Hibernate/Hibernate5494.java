		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// the only methods we are interested in are the LOB creation methods...
			if ( args == null || args.length == 0 ) {
				final String methodName = method.getName();
				if ( "createBlob".equals( methodName ) ) {
					return lobBuilder.createBlob();
				}
				else if ( "createClob".equals( methodName ) ) {
					return lobBuilder.createClob();
				}
				else if ( "createNClob".equals( methodName ) ) {
					return lobBuilder.createNClob();
				}
				else if ( "getMetaData".equals( methodName ) ) {
					return metadata;
				}
			}
			return null;
		}
