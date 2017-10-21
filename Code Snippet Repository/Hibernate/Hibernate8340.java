	public Object instantiate(String entityName, EntityMode entityMode, Serializable id) throws CallbackException {
		if ( ! "org.hibernate.test.interceptor.User".equals( entityName ) ) {
			return null;
		}
		// Simply inject a sample string into new instances
		User instance = new User();
		instance.setName( ( String ) id );
		instance.setInjectedString( injectedString );
		return instance;
	}
