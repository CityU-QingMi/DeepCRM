		private DatabaseMetaData getMetadataProxy(Connection connectionProxy) {
			if ( metadataProxy == null ) {
				// we need to make it
				final DatabaseMetaDataHandler metadataHandler = new DatabaseMetaDataHandler( options, connectionProxy );
				metadataProxy = (DatabaseMetaData) Proxy.newProxyInstance(
						ClassLoader.getSystemClassLoader(),
						new Class[] {DatabaseMetaData.class},
						metadataHandler
				);
			}
			return metadataProxy;
		}
