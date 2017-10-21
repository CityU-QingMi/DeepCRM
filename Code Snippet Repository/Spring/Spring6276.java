	@Override
	public ConnectionProperties getConnectionProperties() {
		return new ConnectionProperties() {
			@Override
			public void setDriverClass(Class<? extends Driver> driverClass) {
				dataSource.setDriverClass(driverClass);
			}

			@Override
			public void setUrl(String url) {
				dataSource.setUrl(url);
			}

			@Override
			public void setUsername(String username) {
				dataSource.setUsername(username);
			}

			@Override
			public void setPassword(String password) {
				dataSource.setPassword(password);
			}
		};
	}
