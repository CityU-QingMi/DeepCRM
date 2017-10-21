	@Test
	public void testHistoryOfO2_2() {
		ListBiowning1Entity o1_1 = getEntityManager().find( ListBiowning1Entity.class, o1_1_id );
		ListBiowning1Entity o1_2 = getEntityManager().find( ListBiowning1Entity.class, o1_2_id );

		ListBiowning2Entity rev1 = getAuditReader().find( ListBiowning2Entity.class, o2_2_id, 1 );
		ListBiowning2Entity rev2 = getAuditReader().find( ListBiowning2Entity.class, o2_2_id, 2 );
		ListBiowning2Entity rev3 = getAuditReader().find( ListBiowning2Entity.class, o2_2_id, 3 );
		ListBiowning2Entity rev4 = getAuditReader().find( ListBiowning2Entity.class, o2_2_id, 4 );
		ListBiowning2Entity rev5 = getAuditReader().find( ListBiowning2Entity.class, o2_2_id, 5 );

		assert TestTools.checkCollection( rev1.getReferences() );
		assert TestTools.checkCollection( rev2.getReferences(), o1_2 );
		assert TestTools.checkCollection( rev3.getReferences(), o1_1, o1_2 );
		assert TestTools.checkCollection( rev4.getReferences(), o1_2 );
		assert TestTools.checkCollection( rev5.getReferences(), o1_1, o1_2 );
	}
