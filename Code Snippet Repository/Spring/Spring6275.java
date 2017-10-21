	protected void shutdownDatabase() {
		if (this.dataSource != null) {
			if (logger.isInfoEnabled()) {
				if (this.dataSource instanceof SimpleDriverDataSource) {
					logger.info(String.format("Shutting down embedded database: url='%s'",
						((SimpleDriverDataSource) this.dataSource).getUrl()));
				}
				else {
					logger.info(String.format("Shutting down embedded database '%s'", this.databaseName));
				}
			}
			if (this.databaseConfigurer != null) {
				this.databaseConfigurer.shutdown(this.dataSource, this.databaseName);
			}
			this.dataSource = null;
		}
	}
