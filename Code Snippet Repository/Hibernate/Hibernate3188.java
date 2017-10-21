	private void checkListeners() {
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo( getClass(), Object.class );
			internalCheckListeners( beanInfo );
		}
		catch (IntrospectionException t) {
			throw new HibernateException( "Unable to validate listener config", t );
		}
		finally {
			if ( beanInfo != null ) {
				// release the jdk internal caches everytime to ensure this
				// plays nicely with destroyable class-loaders
				Introspector.flushFromCaches( getClass() );
			}
		}
	}
