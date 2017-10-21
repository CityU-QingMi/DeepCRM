	public static String toLoggableString(
			Object[] state,
			Type[] types,
			SessionFactoryImplementor factory) {
		final StringBuilder buff = new StringBuilder();
		for ( int i = 0; i < state.length; i++ ) {
			if ( i > 0 ) {
				buff.append( ", " );
			}

			// HHH-11173 - Instead of having to account for unfectched lazy properties in all types, it's done here
			if ( state[i] == LazyPropertyInitializer.UNFETCHED_PROPERTY || !Hibernate.isInitialized( state[i] ) ) {
				buff.append( "<uninitialized>" );
			}
			else {
				buff.append( types[i].toLoggableString( state[i], factory ) );
			}
		}
		return buff.toString();
	}
