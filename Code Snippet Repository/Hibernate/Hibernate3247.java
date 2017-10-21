	public static void visitBeanInfo(Class beanClass, Class stopClass, BeanInfoDelegate delegate) {
		try {
			BeanInfo info = Introspector.getBeanInfo( beanClass, stopClass );
			try {
				delegate.processBeanInfo( info );
			}
			catch ( RuntimeException e ) {
				throw e;
			}
			catch ( InvocationTargetException e ) {
				throw new BeanIntrospectionException( "Error delegating bean info use", e.getTargetException() );
			}
			catch ( Exception e ) {
				throw new BeanIntrospectionException( "Error delegating bean info use", e );
			}
			finally {
				Introspector.flushFromCaches( beanClass );
			}
		}
		catch ( IntrospectionException e ) {
			throw new BeanIntrospectionException( "Unable to determine bean info from class [" + beanClass.getName() + "]", e );
		}
	}
