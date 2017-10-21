	@Override
	@SuppressWarnings("")
	public Object destructure(Object structured, SessionFactoryImplementor factory) {
		final Map map = (Map) structured;
		final String subclass = (String) map.get( SUBCLASS_KEY );
		final Object version = map.get( VERSION_KEY );
		final EntityPersister subclassPersister = factory.getEntityPersister( subclass );
		final String[] names = subclassPersister.getPropertyNames();
		final Serializable[] state = new Serializable[names.length];
		for ( int i = 0; i < names.length; i++ ) {
			state[i] = (Serializable) map.get( names[i] );
		}
		return new StandardCacheEntryImpl(
				state,
				TypeHelper.toLoggableString( state, subclassPersister.getPropertyTypes(), factory ),
				subclass,
				version
		);
	}
