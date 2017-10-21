	@Test
	public void testHistoryOfChild1_2() {
		ParentEntity p1 = getEntityManager().find( ParentEntity.class, p1_id );

		Child1Entity rev1 = getAuditReader().find(
				Child1Entity.class, c1_2_id,
				1
		);
		Child1Entity rev2 = getAuditReader().find(
				Child1Entity.class, c1_2_id,
				2
		);
		Child1Entity rev3 = getAuditReader().find(
				Child1Entity.class, c1_2_id,
				3
		);
		Child1Entity rev4 = getAuditReader().find(
				Child1Entity.class, c1_2_id,
				4
		);
		Child1Entity rev5 = getAuditReader().find(
				Child1Entity.class, c1_2_id,
				5
		);

		assert TestTools.checkCollection( rev1.getParents() );
		assert TestTools.checkCollection( rev2.getParents() );
		assert TestTools.checkCollection( rev3.getParents(), p1 );
		assert TestTools.checkCollection( rev4.getParents(), p1 );
		assert TestTools.checkCollection( rev5.getParents() );
	}
