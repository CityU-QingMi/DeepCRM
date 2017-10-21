	@Test
	public void matchesSpecific() throws Exception {
		editor.setAsText(
			"java.lang.Object.hashCode=PROPAGATION_REQUIRED\n" +
			"java.lang.Object.equals=PROPAGATION_MANDATORY\n" +
			"java.lang.Object.*it=PROPAGATION_SUPPORTS\n" +
			"java.lang.Object.notify=PROPAGATION_SUPPORTS\n" +
			"java.lang.Object.not*=PROPAGATION_REQUIRED");
		TransactionAttributeSource tas = (TransactionAttributeSource) editor.getValue();

		checkTransactionProperties(tas, Object.class.getMethod("hashCode", (Class[]) null),
			TransactionDefinition.PROPAGATION_REQUIRED);
		checkTransactionProperties(tas, Object.class.getMethod("equals", new Class[] { Object.class }),
			TransactionDefinition.PROPAGATION_MANDATORY);
		checkTransactionProperties(tas, Object.class.getMethod("wait", (Class[]) null),
			TransactionDefinition.PROPAGATION_SUPPORTS);
		checkTransactionProperties(tas, Object.class.getMethod("wait", new Class[] { long.class }),
			TransactionDefinition.PROPAGATION_SUPPORTS);
		checkTransactionProperties(tas, Object.class.getMethod("wait", new Class[] { long.class, int.class }),
			TransactionDefinition.PROPAGATION_SUPPORTS);
		checkTransactionProperties(tas, Object.class.getMethod("notify", (Class[]) null),
			TransactionDefinition.PROPAGATION_SUPPORTS);
		checkTransactionProperties(tas, Object.class.getMethod("notifyAll", (Class[]) null),
			TransactionDefinition.PROPAGATION_REQUIRED);
		checkTransactionProperties(tas, Object.class.getMethod("toString", (Class[]) null), -1);
	}
