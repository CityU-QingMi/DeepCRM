	public Object instantiate(Object parent, SharedSessionContractImplementor session)
			throws HibernateException {

		Object result = instantiate( entityMode );

		if ( componentTuplizer.hasParentProperty() && parent != null ) {
			componentTuplizer.setParent(
					result,
					session.getPersistenceContext().proxyFor( parent ),
					session.getFactory()
			);
		}

		return result;
	}
