		public Object getProxy() {
			try {
				final Proxy proxy = (Proxy) proxyClass.newInstance();
				proxy.setHandler( new PassThroughHandler( proxy, proxyClass.getName() ) );
				return proxy;
			}
			catch ( Throwable t ) {
				throw new HibernateException( "Unable to instantiated proxy instance" );
			}
		}
