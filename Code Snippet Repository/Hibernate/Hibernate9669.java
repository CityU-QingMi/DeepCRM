	@Test
	public void testCreateThenDrop(BmtSfStatefulBean ejb) throws Exception {
		assert ejb != null : "Method injected StatefulCMTBean reference was null";

		try {
			ejb.start();
		}
		finally {
			ejb.stop();
		}
	}
