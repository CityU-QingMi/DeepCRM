	@Test
	public void nameMatchTransactionAttributeSourceWithStarAtStartOfMethodName()
			throws NoSuchMethodException {
		NameMatchTransactionAttributeSource tas = new NameMatchTransactionAttributeSource();
		Properties attributes = new Properties();
		attributes.put("*ashCode", "PROPAGATION_REQUIRED");
		tas.setProperties(attributes);
		TransactionAttribute ta = tas.getTransactionAttribute(
				Object.class.getMethod("hashCode", (Class[]) null), null);
		assertNotNull(ta);
		assertEquals(TransactionDefinition.PROPAGATION_REQUIRED, ta.getPropagationBehavior());
	}
