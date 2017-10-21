	public static DatasourceConnectionProviderImpl buildDataSourceConnectionProvider(String dbName) {
		try {
			Class dataSourceClass = ReflectHelper.classForName( DATA_SOURCE, ConnectionProviderBuilder.class );
			DataSource actualDataSource = (DataSource) dataSourceClass.newInstance();
			ReflectHelper.findSetterMethod( dataSourceClass, "URL", String.class ).invoke(
					actualDataSource,
					String.format( URL, dbName )
			);
			ReflectHelper.findSetterMethod( dataSourceClass, "user", String.class ).invoke( actualDataSource, USER );
			ReflectHelper.findSetterMethod( dataSourceClass, "password", String.class )
					.invoke( actualDataSource, PASS );

			final DataSourceInvocationHandler dataSourceInvocationHandler = new DataSourceInvocationHandler(
					actualDataSource );

			DatasourceConnectionProviderImpl connectionProvider = new DatasourceConnectionProviderImpl() {
				@Override
				public void stop() {
					dataSourceInvocationHandler.stop();
				}
			};

			connectionProvider.configure(
					Collections.singletonMap(
							Environment.DATASOURCE,
							Proxy.newProxyInstance(
									Thread.currentThread().getContextClassLoader(),
									new Class[] {DataSource.class},
									dataSourceInvocationHandler
							)
					)
			);
			return connectionProvider;
		}
		catch (Exception e) {
			throw new IllegalArgumentException( e );
		}
	}
