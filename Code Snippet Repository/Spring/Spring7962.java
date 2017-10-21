	@Test
	public void testTransactionCommitWithSharedEntityManagerUnsynchronizedAndPropagationSupports() {
		given(manager.isOpen()).willReturn(true);

		tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);

		tt.execute(status -> {
			bean.sharedEntityManagerUnsynchronized.clear();
			return null;
		});

		verify(manager).clear();
		verify(manager).close();
	}
