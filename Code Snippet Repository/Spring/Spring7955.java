	@Test
	public void testTransactionCommitWithExtendedEntityManagerUnsynchronizedJoined() {
		given(manager.getTransaction()).willReturn(tx);

		tt.execute(status -> {
			bean.extendedEntityManagerUnsynchronized.joinTransaction();
			bean.extendedEntityManagerUnsynchronized.flush();
			return null;
		});

		verify(tx, times(2)).commit();
		verify(manager).flush();
		verify(manager).close();
	}
