	public static Connection getConnection(ConnectionFactory cf, @Nullable ConnectionSpec spec)
			throws CannotGetCciConnectionException {
		try {
			if (spec != null) {
				Assert.notNull(cf, "No ConnectionFactory specified");
				return cf.getConnection(spec);
			}
			else {
				return doGetConnection(cf);
			}
		}
		catch (ResourceException ex) {
			throw new CannotGetCciConnectionException("Could not get CCI Connection", ex);
		}
	}
