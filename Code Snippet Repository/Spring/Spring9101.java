		@Override
		public Object invoke(MethodInvocation methodInvocation) throws Throwable {
			if (methodInvocation.getMethod().getName().equals("setSomething")) {
				assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
				assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
			}
			else {
				assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
				assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
			}
			return methodInvocation.proceed();
		}
