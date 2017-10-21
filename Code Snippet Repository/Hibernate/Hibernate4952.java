		public ProxiedInstantiator(Class componentClass) {
			proxiedClass = componentClass;
			if ( proxiedClass.isInterface() ) {
				factory = Environment.getBytecodeProvider()
						.getProxyFactoryFactory()
						.buildBasicProxyFactory( null, new Class[] { proxiedClass } );
			}
			else {
				factory = Environment.getBytecodeProvider()
						.getProxyFactoryFactory()
						.buildBasicProxyFactory( proxiedClass, null );
			}
		}
