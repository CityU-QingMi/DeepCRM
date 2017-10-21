	@Test
	public void testHistoryOfChild2_1() {
		ParentEntity p2 = getEntityManager().find( ParentEntity.class, p2_id );

		Child2Entity rev1 = getAuditReader().find( Child2Entity.class, c2_1_id, 1 );
		Child2Entity rev2 = getAuditReader().find( Child2Entity.class, c2_1_id, 2 );
		Child2Entity rev3 = getAuditReader().find( Child2Entity.class, c2_1_id, 3 );
		Child2Entity rev4 = getAuditReader().find( Child2Entity.class, c2_1_id, 4 );
		Child2Entity rev5 = getAuditReader().find( Child2Entity.class, c2_1_id, 5 );

		assert TestTools.checkCollection( rev1.getParents() );
		assert TestTools.checkCollection( rev2.getParents(), p2 );
		assert TestTools.checkCollection( rev3.getParents(), p2 );
		assert TestTools.checkCollection( rev4.getParents(), p2 );
		assert TestTools.checkCollection( rev5.getParents(), p2 );
	}
