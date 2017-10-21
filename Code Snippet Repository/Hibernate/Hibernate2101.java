	@Override
	public void configure(Map configValues) {
		if ( this.dataSource == null ) {
			final Object dataSource = configValues.get( Environment.DATASOURCE );
			if ( DataSource.class.isInstance( dataSource ) ) {
				this.dataSource = (DataSource) dataSource;
			}
			else {
				final String dataSourceJndiName = (String) dataSource;
				if ( dataSourceJndiName == null ) {
					throw new HibernateException(
							"DataSource to use was not injected nor specified by [" + Environment.DATASOURCE
									+ "] configuration property"
					);
				}
				if ( jndiService == null ) {
					throw new HibernateException( "Unable to locate JndiService to lookup Datasource" );
				}
				this.dataSource = (DataSource) jndiService.locate( dataSourceJndiName );
			}
		}
		if ( this.dataSource == null ) {
			throw new HibernateException( "Unable to determine appropriate DataSource to use" );
		}

		user = (String) configValues.get( Environment.USER );
		pass = (String) configValues.get( Environment.PASS );
		useCredentials = user != null || pass != null;
		available = true;
	}
