	@Test
	public void testTransactionCommitWithSharedEntityManagerAndPropagationSupports() {
		given(manager.isOpen()).willReturn(true);

		tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);

		tt.execute(status -> {
			bean.sharedEntityManager.clear();
			return null;
		});

		verify(manager).clear();
		verify(manager).close();
	}
