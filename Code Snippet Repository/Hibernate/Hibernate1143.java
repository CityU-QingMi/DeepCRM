		public BasicProxyFactoryImpl(Class superClass, Class[] interfaces) {
			if ( superClass == null && ( interfaces == null || interfaces.length < 1 ) ) {
				throw new AssertionFailure( "attempting to build proxy without any superclass or interfaces" );
			}

			final javassist.util.proxy.ProxyFactory factory = new javassist.util.proxy.ProxyFactory();
			factory.setFilter( FINALIZE_FILTER );
			if ( superClass != null ) {
				factory.setSuperclass( superClass );
			}
			if ( interfaces != null && interfaces.length > 0 ) {
				factory.setInterfaces( interfaces );
			}
			proxyClass = factory.createClass();
		}
