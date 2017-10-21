	@Test
	public void testTransactionCommitWithExtendedEntityManagerUnsynchronized() {
		given(manager.getTransaction()).willReturn(tx);

		tt.execute(status -> {
			bean.extendedEntityManagerUnsynchronized.flush();
			return null;
		});

		verify(tx).commit();
		verify(manager).flush();
		verify(manager).close();
	}
