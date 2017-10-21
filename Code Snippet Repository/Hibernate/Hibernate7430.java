	@Override
	@SuppressWarnings("")
	protected void addSettings(Map settings) {
		super.addSettings( settings );

		settings.put( Environment.RELEASE_CONNECTIONS, ConnectionReleaseMode.ON_CLOSE.toString() );
		settings.put( Environment.CONNECTION_PROVIDER, UserSuppliedConnectionProviderImpl.class.getName() );

		Connection connection;
		try {
			connection = cp.getConnection();
			try {
				boolean supportsScroll = connection.getMetaData().supportsResultSetType( ResultSet.TYPE_SCROLL_INSENSITIVE );
				settings.put( Environment.USE_SCROLLABLE_RESULTSET, "" + supportsScroll );
			}
			finally {
				connection.close();
			}
		}
		catch (SQLException ignore) {
		}
	}
