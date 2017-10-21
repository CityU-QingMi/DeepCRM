	@Test
	public void testHistoryOfEme2() {
		EmbeddableMapEntity rev1 = getAuditReader().find( EmbeddableMapEntity.class, eme2_id, 1 );
		EmbeddableMapEntity rev2 = getAuditReader().find( EmbeddableMapEntity.class, eme2_id, 2 );
		EmbeddableMapEntity rev3 = getAuditReader().find( EmbeddableMapEntity.class, eme2_id, 3 );
		EmbeddableMapEntity rev4 = getAuditReader().find( EmbeddableMapEntity.class, eme2_id, 4 );

		Assert.assertEquals( TestTools.makeMap( "1", c3_1 ), rev1.getComponentMap() );
		Assert.assertEquals( TestTools.makeMap( "1", c3_1 ), rev2.getComponentMap() );
		Assert.assertEquals( TestTools.makeMap( "1", c3_2 ), rev3.getComponentMap() );
		Assert.assertEquals( TestTools.makeMap( "1", c3_2 ), rev4.getComponentMap() );
	}
