	@Override
	protected void autoDiscoverTypes(ResultSet rs) {
		try {
			JdbcResultMetadata metadata = new JdbcResultMetadata( getFactory(), rs );
			rowProcessor.prepareForAutoDiscovery( metadata );

			List<String> aliases = new ArrayList<>();
			List<Type> types = new ArrayList<>();
			for ( ResultColumnProcessor resultProcessor : rowProcessor.getColumnProcessors() ) {
				resultProcessor.performDiscovery( metadata, types, aliases );
			}

			validateAliases( aliases );

			resultTypes = ArrayHelper.toTypeArray( types );
			transformerAliases = ArrayHelper.toStringArray( aliases );
		}
		catch (SQLException e) {
			throw new HibernateException( "Exception while trying to autodiscover types.", e );
		}
	}
