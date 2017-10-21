		@Override
		public Dialect resolveDialect(DialectResolutionInfo info) {
			String databaseName = info.getDatabaseName();
			int databaseMajorVersion = info.getDatabaseMajorVersion();
			if ( "MyDatabase1".equals( databaseName ) ) {
				return new MyDialect1();
			}
			if ( "MyDatabase2".equals( databaseName ) ) {
				if ( databaseMajorVersion >= 2 ) {
					return new MyDialect22();
				}
				if ( databaseMajorVersion >= 1 ) {
					return new MyDialect21();
				}
			}
			return null;
		}
