	@Test
	public void testTransactionCommitWithExtendedEntityManagerAndPropagationSupports() {
		given(manager.isOpen()).willReturn(true);

		tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);

		tt.execute(status -> {
			bean.extendedEntityManager.flush();
			return null;
		});

		verify(manager).flush();
	}
