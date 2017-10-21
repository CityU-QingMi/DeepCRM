	public FetchingScrollableResultsImpl(
			ResultSet rs,
			PreparedStatement ps,
			SharedSessionContractImplementor sess,
			Loader loader,
			QueryParameters queryParameters,
			Type[] types,
			HolderInstantiator holderInstantiator) {
		super( rs, ps, sess, loader, queryParameters, types, holderInstantiator );
	}
