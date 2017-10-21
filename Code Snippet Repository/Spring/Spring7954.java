	@Test
	public void testTransactionCommitWithSharedEntityManagerUnsynchronizedJoined() {
		given(manager.getTransaction()).willReturn(tx);

		tt.execute(status -> {
			bean.sharedEntityManagerUnsynchronized.joinTransaction();
			bean.sharedEntityManagerUnsynchronized.flush();
			return null;
		});

		verify(tx).commit();
		verify(manager).flush();
		verify(manager, times(2)).close();
	}
