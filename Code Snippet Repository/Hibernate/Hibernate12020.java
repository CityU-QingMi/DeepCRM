		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if ("getConnection".equals(method.getName())) {
				if(actualConnection == null) {
					actualConnection = (Connection) method.invoke( target, args);
					connectionProxy = (Connection) Proxy.newProxyInstance(
							this.getClass().getClassLoader(),
							new Class[] { Connection.class },
							new ConnectionInvocationHandler( actualConnection )
					);
				}
			}
			return connectionProxy;
		}
