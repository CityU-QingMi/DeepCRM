		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			try {
				if (method.getName().equals("equals")) {
					// Only consider equal when proxies are identical.
					return (proxy == args[0]);
				}
				else if (method.getName().equals("hashCode")) {
					// Use hashCode of EntityManagerFactory proxy.
					return System.identityHashCode(proxy);
				}
				else if (method.getName().equals("getProperties")) {
					return getProperties();
				}
				else if (method.getName().equals("getWrappedObject")) {
					// Call coming in through InfrastructureProxy interface...
					return getSessionFactory();
				}
				// Regular delegation to the target SessionFactory,
				// enforcing its full initialization...
				return method.invoke(getSessionFactory(), args);
			}
			catch (InvocationTargetException ex) {
				throw ex.getTargetException();
			}
		}
