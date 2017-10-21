	@Test
	public void testHistoryOfChild1_1() {
		ParentEntity p1 = getEntityManager().find( ParentEntity.class, p1_id );
		ParentEntity p2 = getEntityManager().find( ParentEntity.class, p2_id );

		Child1Entity rev1 = getAuditReader().find( Child1Entity.class, c1_1_id, 1 );
		Child1Entity rev2 = getAuditReader().find( Child1Entity.class, c1_1_id, 2 );
		Child1Entity rev3 = getAuditReader().find( Child1Entity.class, c1_1_id, 3 );
		Child1Entity rev4 = getAuditReader().find( Child1Entity.class, c1_1_id, 4 );
		Child1Entity rev5 = getAuditReader().find( Child1Entity.class, c1_1_id, 5 );

		assert TestTools.checkCollection( rev1.getParents() );
		assert TestTools.checkCollection( rev2.getParents(), p1 );
		assert TestTools.checkCollection( rev3.getParents(), p1, p2 );
		assert TestTools.checkCollection( rev4.getParents(), p2 );
		assert TestTools.checkCollection( rev5.getParents(), p2 );
	}
