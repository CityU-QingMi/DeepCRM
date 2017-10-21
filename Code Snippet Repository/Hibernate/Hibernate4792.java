	public static JdbcMetadaAccessStrategy interpretHbm2ddlSetting(Object value) {
		if(value == null){
			return GROUPED;
		}
		String name = value.toString();
		if ( StringHelper.isEmpty( name ) || GROUPED.strategy.equals( name ) ) {
			return GROUPED;
		}
		else if ( INDIVIDUALLY.strategy.equals( name ) ) {
			return INDIVIDUALLY;
		}
		else {
			throw new IllegalArgumentException( "Unrecognized `" + AvailableSettings.HBM2DDL_JDBC_METADATA_EXTRACTOR_STRATEGY + "` value : " + name );
		}

	}
