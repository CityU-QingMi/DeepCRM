	@SuppressWarnings("")
	@Override
	public void configure(Map props) throws HibernateException {
		try {
			LOGGER.debug( "Configuring HikariCP" );

			hcfg = HikariConfigurationUtil.loadConfiguration( props );
			hds = new HikariDataSource( hcfg );

		}
		catch (Exception e) {
			throw new HibernateException( e );
		}

		LOGGER.debug( "HikariCP Configured" );
	}
