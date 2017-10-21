	private void internalCheckListeners(BeanInfo beanInfo) {
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		try {
			for ( PropertyDescriptor pd : pds ) {
				final Object listener = pd.getReadMethod().invoke( this, READER_METHOD_ARGS );
				if ( listener == null ) {
					throw new HibernateException( "Listener [" + pd.getName() + "] was null" );
				}
				if ( listener.getClass().isArray() ) {
					Object[] listenerArray = (Object[]) listener;
					for ( Object aListenerArray : listenerArray ) {
						if ( aListenerArray == null ) {
							throw new HibernateException( "Listener in [" + pd.getName() + "] was null" );
						}
					}
				}
			}
		}
		catch (HibernateException e) {
			throw e;
		}
		catch (Throwable t) {
			throw new HibernateException( "Unable to validate listener config" );
		}
	}
