	private void checkTransactionProperties(TransactionAttributeSource tas, Method method, int propagationBehavior) {
		TransactionAttribute ta = tas.getTransactionAttribute(method, null);
		if (propagationBehavior >= 0) {
			assertNotNull(ta);
			assertEquals(TransactionDefinition.ISOLATION_DEFAULT, ta.getIsolationLevel());
			assertEquals(propagationBehavior, ta.getPropagationBehavior());
		}
		else {
			assertNull(ta);
		}
	}
