	protected AbstractScrollableResults(
			ResultSet rs,
			PreparedStatement ps,
			SharedSessionContractImplementor sess,
			Loader loader,
			QueryParameters queryParameters,
			Type[] types,
			HolderInstantiator holderInstantiator) {
		this.resultSet = rs;
		this.ps = ps;
		this.session = sess;
		this.loader = loader;
		this.queryParameters = queryParameters;
		this.types = types;
		this.holderInstantiator = holderInstantiator != null && holderInstantiator.isRequired()
				? holderInstantiator
				: null;
	}
