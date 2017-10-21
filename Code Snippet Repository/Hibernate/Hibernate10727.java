	@Test
	public void testHistoryOfParent1() {
		Child1Entity c1_1 = getEntityManager().find( Child1Entity.class, c1_1_id );
		Child1Entity c1_2 = getEntityManager().find( Child1Entity.class, c1_2_id );
		Child2Entity c2_2 = getEntityManager().find( Child2Entity.class, c2_2_id );

		ParentEntity rev1 = getAuditReader().find( ParentEntity.class, p1_id, 1 );
		ParentEntity rev2 = getAuditReader().find( ParentEntity.class, p1_id, 2 );
		ParentEntity rev3 = getAuditReader().find( ParentEntity.class, p1_id, 3 );
		ParentEntity rev4 = getAuditReader().find( ParentEntity.class, p1_id, 4 );
		ParentEntity rev5 = getAuditReader().find( ParentEntity.class, p1_id, 5 );

		assert TestTools.checkCollection( rev1.getChildren1() );
		assert TestTools.checkCollection( rev2.getChildren1(), c1_1 );
		assert TestTools.checkCollection( rev3.getChildren1(), c1_1, c1_2 );
		assert TestTools.checkCollection( rev4.getChildren1(), c1_2 );
		assert TestTools.checkCollection( rev5.getChildren1() );

		assert TestTools.checkCollection( rev1.getChildren2() );
		assert TestTools.checkCollection( rev2.getChildren2() );
		assert TestTools.checkCollection( rev3.getChildren2(), c2_2 );
		assert TestTools.checkCollection( rev4.getChildren2(), c2_2 );
		assert TestTools.checkCollection( rev5.getChildren2(), c2_2 );
	}
