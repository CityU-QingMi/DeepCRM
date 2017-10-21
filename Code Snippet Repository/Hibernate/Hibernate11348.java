	@SuppressWarnings("")
	public static HikariConfig loadConfiguration(Map props) {
		Properties hikariProps = new Properties();
		copyProperty( AvailableSettings.AUTOCOMMIT, props, "autoCommit", hikariProps );

		copyProperty( AvailableSettings.DRIVER, props, "driverClassName", hikariProps );
		copyProperty( AvailableSettings.URL, props, "jdbcUrl", hikariProps );
		copyProperty( AvailableSettings.USER, props, "username", hikariProps );
		copyProperty( AvailableSettings.PASS, props, "password", hikariProps );

		copyIsolationSetting( props, hikariProps );

		for ( Object keyo : props.keySet() ) {
			if ( !(keyo instanceof String) ) {
				continue;
			}
			String key = (String) keyo;
			if ( key.startsWith( CONFIG_PREFIX ) ) {
				hikariProps.setProperty( key.substring( CONFIG_PREFIX.length() ), (String) props.get( key ) );
			}
		}

		return new HikariConfig( hikariProps );
	}
