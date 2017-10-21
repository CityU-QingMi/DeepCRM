	@Test
	public void matchesAll() throws Exception {
		editor.setAsText("java.lang.Object.*=PROPAGATION_REQUIRED");
		TransactionAttributeSource tas = (TransactionAttributeSource) editor.getValue();

		checkTransactionProperties(tas, Object.class.getMethod("hashCode", (Class[]) null),
			TransactionDefinition.PROPAGATION_REQUIRED);
		checkTransactionProperties(tas, Object.class.getMethod("equals", new Class[] { Object.class }),
			TransactionDefinition.PROPAGATION_REQUIRED);
		checkTransactionProperties(tas, Object.class.getMethod("wait", (Class[]) null),
			TransactionDefinition.PROPAGATION_REQUIRED);
		checkTransactionProperties(tas, Object.class.getMethod("wait", new Class[] { long.class }),
			TransactionDefinition.PROPAGATION_REQUIRED);
		checkTransactionProperties(tas, Object.class.getMethod("wait", new Class[] { long.class, int.class }),
			TransactionDefinition.PROPAGATION_REQUIRED);
		checkTransactionProperties(tas, Object.class.getMethod("notify", (Class[]) null),
			TransactionDefinition.PROPAGATION_REQUIRED);
		checkTransactionProperties(tas, Object.class.getMethod("notifyAll", (Class[]) null),
			TransactionDefinition.PROPAGATION_REQUIRED);
		checkTransactionProperties(tas, Object.class.getMethod("toString", (Class[]) null),
			TransactionDefinition.PROPAGATION_REQUIRED);
	}
