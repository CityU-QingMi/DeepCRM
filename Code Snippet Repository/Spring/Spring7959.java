	@Test
	public void testTransactionCommitWithExtendedEntityManager() {
		given(manager.getTransaction()).willReturn(tx);

		tt.execute(status -> {
			bean.extendedEntityManager.flush();
			return null;
		});

		verify(tx, times(2)).commit();
		verify(manager).flush();
		verify(manager).close();
	}
