	@SuppressWarnings("")
	private static ListenerFactory buildBeanManagerListenerFactory(
			Object beanManagerRef,
			String listenerClass) {
		try {
			// specifically using our ClassLoader here...
			final Class beanManagerListenerFactoryClass = ListenerFactoryBuilder.class.getClassLoader()
					.loadClass( listenerClass );
			final Method beanManagerListenerFactoryBuilderMethod = beanManagerListenerFactoryClass.getMethod(
					CDI_LISTENER_FACTORY_METHOD_NAME,
					Object.class
			);

			try {
				return (ListenerFactory) beanManagerListenerFactoryBuilderMethod.invoke( null, beanManagerRef );
			}
			catch (InvocationTargetException e) {
				throw e.getTargetException();
			}
		}
		catch (ClassNotFoundException e) {
			throw new HibernateException(
					"Could not locate BeanManager ListenerFactory class [" + listenerClass + "] to handle CDI extensions",
					e
			);
		}
		catch (HibernateException e) {
			throw e;
		}
		catch (Throwable e) {
			throw new HibernateException(
					"Could not access BeanManager ListenerFactory class [" + listenerClass + "] to handle CDI extensions",
					e
			);
		}
	}
