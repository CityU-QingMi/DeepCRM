	@Test
	public void testHistoryOfEme1() {
		EmbeddableMapEntity rev1 = getAuditReader().find( EmbeddableMapEntity.class, eme1_id, 1 );
		EmbeddableMapEntity rev2 = getAuditReader().find( EmbeddableMapEntity.class, eme1_id, 2 );
		EmbeddableMapEntity rev3 = getAuditReader().find( EmbeddableMapEntity.class, eme1_id, 3 );
		EmbeddableMapEntity rev4 = getAuditReader().find( EmbeddableMapEntity.class, eme1_id, 4 );

		Assert.assertEquals( Collections.EMPTY_MAP, rev1.getComponentMap() );
		Assert.assertEquals( TestTools.makeMap( "1", c3_1, "2", c3_2 ), rev2.getComponentMap() );
		Assert.assertEquals( TestTools.makeMap( "2", c3_2 ), rev3.getComponentMap() );
		Assert.assertEquals( TestTools.makeMap( "2", c3_2 ), rev4.getComponentMap() );
	}
