	@Override
	public Object instantiate(Object parent, SharedSessionContractImplementor session) throws HibernateException {
		final boolean useParent = parent != null &&
				//TODO: Yuck! This is not quite good enough, it's a quick
				//hack around the problem of having a to-one association
				//that refers to an embedded component:
				super.getReturnedClass().isInstance( parent );

		return useParent ? parent : super.instantiate( parent, session );
	}
