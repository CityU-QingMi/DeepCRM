	public WebSphereDataSourceAdapter() {
		try {
			this.wsDataSourceClass = getClass().getClassLoader().loadClass("com.ibm.websphere.rsadapter.WSDataSource");
			Class<?> jdbcConnSpecClass = getClass().getClassLoader().loadClass("com.ibm.websphere.rsadapter.JDBCConnectionSpec");
			Class<?> wsrraFactoryClass = getClass().getClassLoader().loadClass("com.ibm.websphere.rsadapter.WSRRAFactory");
			this.newJdbcConnSpecMethod = wsrraFactoryClass.getMethod("createJDBCConnectionSpec", (Class<?>[]) null);
			this.wsDataSourceGetConnectionMethod =
					this.wsDataSourceClass.getMethod("getConnection", jdbcConnSpecClass);
			this.setTransactionIsolationMethod =
					jdbcConnSpecClass.getMethod("setTransactionIsolation", int.class);
			this.setReadOnlyMethod = jdbcConnSpecClass.getMethod("setReadOnly", Boolean.class);
			this.setUserNameMethod = jdbcConnSpecClass.getMethod("setUserName", String.class);
			this.setPasswordMethod = jdbcConnSpecClass.getMethod("setPassword", String.class);
		}
		catch (Exception ex) {
			throw new IllegalStateException(
					"Could not initialize WebSphereDataSourceAdapter because WebSphere API classes are not available: " + ex);
		}
	}
