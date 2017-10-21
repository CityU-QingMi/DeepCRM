	@Override
	public String toLoggableString(Object value, SessionFactoryImplementor factory) throws HibernateException {
		//TODO: terrible implementation!
		if ( value == null ) {
			return "null";
		}
		if ( value == LazyPropertyInitializer.UNFETCHED_PROPERTY || !Hibernate.isInitialized( value ) ) {
			return  "<uninitialized>";
		}
		Class valueClass = HibernateProxyHelper.getClassWithoutInitializingProxy( value );
		return factory.getTypeHelper().entity( valueClass ).toLoggableString( value, factory );
	}
