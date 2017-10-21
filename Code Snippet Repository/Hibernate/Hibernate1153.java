	private Serializable getIdentifier(EventSource session, Object obj) {
		Serializable id = null;
		if ( obj != null ) {
			id = session.getContextEntityIdentifier( obj );
			if ( id == null ) {
				id = session.getSessionFactory().getMetamodel().entityPersister( obj.getClass() ).getIdentifier( obj, session );
			}
		}
		return id;
	}
