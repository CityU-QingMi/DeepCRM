	public DataSourceUtils createDataSourceUtil(ServiceRegistry serviceRegistry) {
		super.createDataSourceUtil( serviceRegistry );
		try {
			return new GeoDBDataSourceUtils( driver(), url(), user(), passwd(), getSQLExpressionTemplate() );
		}
		catch (SQLException e) {
			throw new RuntimeException( e );
		}
		catch (IOException e) {
			throw new RuntimeException( e );
		}
	}
