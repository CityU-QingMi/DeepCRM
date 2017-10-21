	@Test
	public void testTransactionCommitWithSharedEntityManager() {
		given(manager.getTransaction()).willReturn(tx);

		tt.execute(status -> {
			bean.sharedEntityManager.flush();
			return null;
		});

		verify(tx).commit();
		verify(manager).flush();
		verify(manager).close();
	}
