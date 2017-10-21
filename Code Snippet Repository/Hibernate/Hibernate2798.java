	public QueryTranslatorImpl(
			String queryIdentifier,
			String queryString,
			Map enabledFilters,
			SessionFactoryImplementor factory) {
		super( factory );
		this.queryIdentifier = queryIdentifier;
		this.queryString = queryString;
		this.enabledFilters = enabledFilters;
	}
