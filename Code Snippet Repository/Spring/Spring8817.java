		@Override
		@Nullable
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// Invocation on Connection interface coming in...

			if (method.getName().equals("equals")) {
				// Only consider equal when proxies are identical.
				return (proxy == args[0]);
			}
			else if (method.getName().equals("hashCode")) {
				// Use hashCode of Connection proxy.
				return System.identityHashCode(proxy);
			}
			else if (method.getName().equals("getLocalTransaction")) {
				if (ConnectionFactoryUtils.isConnectionTransactional(this.target, this.connectionFactory)) {
					throw new javax.resource.spi.IllegalStateException(
							"Local transaction handling not allowed within a managed transaction");
				}
			}
			else if (method.getName().equals("close")) {
				// Handle close method: only close if not within a transaction.
				ConnectionFactoryUtils.doReleaseConnection(this.target, this.connectionFactory);
				return null;
			}

			// Invoke method on target Connection.
			try {
				return method.invoke(this.target, args);
			}
			catch (InvocationTargetException ex) {
				throw ex.getTargetException();
			}
		}
