	private static Session doUnbind(SessionFactory factory, boolean releaseMapIfEmpty) {
		Session session = null;
		final Map sessionMap = sessionMap();
		if ( sessionMap != null ) {
			session = (Session) sessionMap.remove( factory );
			if ( releaseMapIfEmpty && sessionMap.isEmpty() ) {
				CONTEXT_TL.set( null );
			}
		}
		return session;
	}
