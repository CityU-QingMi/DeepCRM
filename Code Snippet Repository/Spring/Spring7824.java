		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// Invocation on Query interface coming in...

			if (method.getName().equals("equals")) {
				// Only consider equal when proxies are identical.
				return (proxy == args[0]);
			}
			else if (method.getName().equals("hashCode")) {
				// Use hashCode of EntityManager proxy.
				return hashCode();
			}
			else if (method.getName().equals("unwrap")) {
				// Handle JPA 2.0 unwrap method - could be a proxy match.
				Class<?> targetClass = (Class<?>) args[0];
				if (targetClass == null) {
					return this.target;
				}
				else if (targetClass.isInstance(proxy)) {
					return proxy;
				}
			}

			// Invoke method on actual Query object.
			try {
				Object retVal = method.invoke(this.target, args);
				return (retVal == this.target ? proxy : retVal);
			}
			catch (InvocationTargetException ex) {
				throw ex.getTargetException();
			}
			finally {
				if (queryTerminatingMethods.contains(method.getName())) {
					// Actual execution of the query: close the EntityManager right
					// afterwards, since that was the only reason we kept it open.
					EntityManagerFactoryUtils.closeEntityManager(this.em);
					this.em = null;
				}
			}
		}
