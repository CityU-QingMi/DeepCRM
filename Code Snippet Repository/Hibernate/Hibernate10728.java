	@Test
	public void testHistoryOfParent2() {
		Child1Entity c1_1 = getEntityManager().find( Child1Entity.class, c1_1_id );
		Child2Entity c2_1 = getEntityManager().find( Child2Entity.class, c2_1_id );
		Child2Entity c2_2 = getEntityManager().find( Child2Entity.class, c2_2_id );

		ParentEntity rev1 = getAuditReader().find( ParentEntity.class, p2_id, 1 );
		ParentEntity rev2 = getAuditReader().find( ParentEntity.class, p2_id, 2 );
		ParentEntity rev3 = getAuditReader().find( ParentEntity.class, p2_id, 3 );
		ParentEntity rev4 = getAuditReader().find( ParentEntity.class, p2_id, 4 );
		ParentEntity rev5 = getAuditReader().find( ParentEntity.class, p2_id, 5 );

		assert TestTools.checkCollection( rev1.getChildren1() );
		assert TestTools.checkCollection( rev2.getChildren1() );
		assert TestTools.checkCollection( rev3.getChildren1(), c1_1 );
		assert TestTools.checkCollection( rev4.getChildren1(), c1_1 );
		assert TestTools.checkCollection( rev5.getChildren1(), c1_1 );

		assert TestTools.checkCollection( rev1.getChildren2() );
		assert TestTools.checkCollection( rev2.getChildren2(), c2_1 );
		assert TestTools.checkCollection( rev3.getChildren2(), c2_1 );
		assert TestTools.checkCollection( rev4.getChildren2(), c2_1, c2_2 );
		assert TestTools.checkCollection( rev5.getChildren2(), c2_1 );
	}
