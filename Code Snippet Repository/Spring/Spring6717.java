		@Override
		@Nullable
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// Invocation on SessionProxy interface coming in...

			if (method.getName().equals("equals")) {
				// Only consider equal when proxies are identical.
				return (proxy == args[0]);
			}
			else if (method.getName().equals("hashCode")) {
				// Use hashCode of Connection proxy.
				return System.identityHashCode(proxy);
			}
			else if (method.getName().equals("commit")) {
				throw new TransactionInProgressException("Commit call not allowed within a managed transaction");
			}
			else if (method.getName().equals("rollback")) {
				throw new TransactionInProgressException("Rollback call not allowed within a managed transaction");
			}
			else if (method.getName().equals("close")) {
				// Handle close method: not to be closed within a transaction.
				return null;
			}
			else if (method.getName().equals("getTargetSession")) {
				// Handle getTargetSession method: return underlying Session.
				return this.target;
			}

			// Invoke method on target Session.
			try {
				return method.invoke(this.target, args);
			}
			catch (InvocationTargetException ex) {
				throw ex.getTargetException();
			}
		}
