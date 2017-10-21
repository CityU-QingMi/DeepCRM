			@Override
			public void registerSynchronization(final Synchronization synchronization)
					throws RollbackException, IllegalStateException,
					SystemException {

				final InvocationHandler ih = new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if ( "afterCompletion".equals( method.getName() ) ) {
							int status = args[2].equals(Boolean.TRUE) ?
									Status.STATUS_COMMITTED :
									Status.STATUS_UNKNOWN;
							synchronization.afterCompletion(status);
						}
						else if ( "beforeCompletion".equals( method.getName() ) ) {
							synchronization.beforeCompletion();
						}
						else if ( "toString".equals( method.getName() ) ) {
							return synchronization.toString();
						}
						return null;
					}

				};

				final Object synchronizationCallback = Proxy.newProxyInstance(
						getClass().getClassLoader(),
						new Class[] {synchronizationCallbackClass},
						ih
				);

				try {
					registerSynchronizationMethod.invoke( extendedJTATransaction, synchronizationCallback );
				}
				catch (Exception e) {
					throw new HibernateException(e);
				}

			}
