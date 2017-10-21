	@Test
	public void testHistoryOfO1_2() {
		ListBiowning2Entity o2_1 = getEntityManager().find( ListBiowning2Entity.class, o2_1_id );
		ListBiowning2Entity o2_2 = getEntityManager().find( ListBiowning2Entity.class, o2_2_id );

		ListBiowning1Entity rev1 = getAuditReader().find( ListBiowning1Entity.class, o1_2_id, 1 );
		ListBiowning1Entity rev2 = getAuditReader().find( ListBiowning1Entity.class, o1_2_id, 2 );
		ListBiowning1Entity rev3 = getAuditReader().find( ListBiowning1Entity.class, o1_2_id, 3 );
		ListBiowning1Entity rev4 = getAuditReader().find( ListBiowning1Entity.class, o1_2_id, 4 );
		ListBiowning1Entity rev5 = getAuditReader().find( ListBiowning1Entity.class, o1_2_id, 5 );

		assert TestTools.checkCollection( rev1.getReferences() );
		assert TestTools.checkCollection( rev2.getReferences(), o2_2 );
		assert TestTools.checkCollection( rev3.getReferences(), o2_2 );
		assert TestTools.checkCollection( rev4.getReferences(), o2_1, o2_2 );
		assert TestTools.checkCollection( rev5.getReferences(), o2_2 );
	}
