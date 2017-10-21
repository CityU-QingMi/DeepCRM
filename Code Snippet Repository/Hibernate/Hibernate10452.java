	@Test
	public void testHistoryOfEse1() {
		EmbeddableSetEntity rev1 = getAuditReader().find( EmbeddableSetEntity.class, ese1_id, 1 );
		EmbeddableSetEntity rev2 = getAuditReader().find( EmbeddableSetEntity.class, ese1_id, 2 );
		EmbeddableSetEntity rev3 = getAuditReader().find( EmbeddableSetEntity.class, ese1_id, 3 );
		EmbeddableSetEntity rev4 = getAuditReader().find( EmbeddableSetEntity.class, ese1_id, 4 );
		EmbeddableSetEntity rev5 = getAuditReader().find( EmbeddableSetEntity.class, ese1_id, 5 );
		EmbeddableSetEntity rev6 = getAuditReader().find( EmbeddableSetEntity.class, ese1_id, 6 );
		EmbeddableSetEntity rev7 = getAuditReader().find( EmbeddableSetEntity.class, ese1_id, 7 );

		assertEquals( TestTools.makeSet( c3_1, c3_3 ), rev1.getComponentSet() );
		assertEquals( TestTools.makeSet( c3_1, c3_2, c3_3 ), rev2.getComponentSet() );
		assertEquals( TestTools.makeSet( c3_1, c3_3 ), rev3.getComponentSet() );
		assertEquals( TestTools.makeSet( c3_1, c3_2, c3_3, c3_4 ), rev4.getComponentSet() );
		assertEquals( TestTools.makeSet( c3_1, c3_3 ), rev5.getComponentSet() );
		assertEquals( TestTools.makeSet( c3_2, c3_4 ), rev6.getComponentSet() );
		assertEquals( TestTools.makeSet( c3_2, c3_4, c3_1 ), rev7.getComponentSet() );
	}
