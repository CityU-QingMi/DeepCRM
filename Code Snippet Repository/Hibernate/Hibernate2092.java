	public BasicConnectionCreator(
			ServiceRegistryImplementor serviceRegistry,
			String url,
			Properties connectionProps,
			boolean autocommit,
			Integer isolation) {
		this.serviceRegistry = serviceRegistry;
		this.url = url;
		this.connectionProps = connectionProps;
		this.autoCommit = autocommit;
		this.isolation = isolation;
	}
