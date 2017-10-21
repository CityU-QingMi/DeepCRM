	private Object copyListeners() {
		Object copy = null;
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo( getClass(), Object.class );
			internalCheckListeners( beanInfo );
			copy = getClass().newInstance();
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for ( PropertyDescriptor pd : pds ) {
				try {
					pd.getWriteMethod().invoke(
							copy,
							pd.getReadMethod().invoke( this, READER_METHOD_ARGS )
					);
				}
				catch (Throwable t) {
					throw new HibernateException( "Unable copy copy listener [" + pd.getName() + "]" );
				}
			}
		}
		catch (Exception t) {
			throw new HibernateException( "Unable to copy listeners", t );
		}
		finally {
			if ( beanInfo != null ) {
				// release the jdk internal caches everytime to ensure this
				// plays nicely with destroyable class-loaders
				Introspector.flushFromCaches( getClass() );
			}
		}

		return copy;
	}
