	@Override
	public void initialize(Serializable id, SharedSessionContractImplementor session)
			throws HibernateException {
		loadCollectionSubselect( 
				session, 
				keys, 
				values,
				types,
				namedParameters,
				getKeyType() 
		);
	}
