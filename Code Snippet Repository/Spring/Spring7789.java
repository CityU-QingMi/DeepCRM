	Object invokeProxyMethod(Method method, @Nullable Object[] args) throws Throwable {
		if (method.getDeclaringClass().isAssignableFrom(EntityManagerFactoryInfo.class)) {
			return method.invoke(this, args);
		}
		else if (method.getName().equals("createEntityManager") && args != null && args.length > 0 &&
				args[0] != null && args[0].getClass().isEnum() && "SYNCHRONIZED".equals(args[0].toString())) {
			// JPA 2.1's createEntityManager(SynchronizationType, Map)
			// Redirect to plain createEntityManager and add synchronization semantics through Spring proxy
			EntityManager rawEntityManager = (args.length > 1 ?
					getNativeEntityManagerFactory().createEntityManager((Map<?, ?>) args[1]) :
					getNativeEntityManagerFactory().createEntityManager());
			return ExtendedEntityManagerCreator.createApplicationManagedEntityManager(rawEntityManager, this, true);
		}

		// Look for Query arguments, primarily JPA 2.1's addNamedQuery(String, Query)
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				Object arg = args[i];
				if (arg instanceof Query && Proxy.isProxyClass(arg.getClass())) {
					// Assumably a Spring-generated proxy from SharedEntityManagerCreator:
					// since we're passing it back to the native EntityManagerFactory,
					// let's unwrap it to the original Query object from the provider.
					try {
						args[i] = ((Query) arg).unwrap(null);
					}
					catch (RuntimeException ex) {
						// Ignore - simply proceed with given Query object then
					}
				}
			}
		}

		// Standard delegation to the native factory, just post-processing EntityManager return values
		Object retVal = method.invoke(getNativeEntityManagerFactory(), args);
		if (retVal instanceof EntityManager) {
			// Any other createEntityManager variant - expecting non-synchronized semantics
			EntityManager rawEntityManager = (EntityManager) retVal;
			retVal = ExtendedEntityManagerCreator.createApplicationManagedEntityManager(rawEntityManager, this, false);
		}
		return retVal;
	}
