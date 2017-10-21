	@Test
	public void testHistoryExists() {
		Master rev1_1 = getAuditReader().find( Master.class, m1_id, 1 );
		Master rev1_2 = getAuditReader().find( Master.class, m1_id, 2 );
		Master rev1_3 = getAuditReader().find( Master.class, m1_id, 3 );
		Master rev1_4 = getAuditReader().find( Master.class, m1_id, 4 );

		assert (rev1_1 != null);
		assert (rev1_2 != null);
		assert (rev1_3 != null);
		assert (rev1_4 != null);
	}
