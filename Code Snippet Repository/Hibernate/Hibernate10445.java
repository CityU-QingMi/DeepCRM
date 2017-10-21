	@Test
	public void testRevisionsCounts() {
		assertEquals(
				Arrays.asList( 1, 2, 3, 4, 5, 7, 8 ),
				getAuditReader().getRevisions( EmbeddableListEntity2.class, ele_id1 )
		);
		assertEquals(
				Arrays.asList( 1 ), getAuditReader().getRevisions( StrTestNoProxyEntity.class, entity1.getId() )
		);
		assertEquals(
				Arrays.asList( 1 ), getAuditReader().getRevisions( StrTestNoProxyEntity.class, entity2.getId() )
		);
		assertEquals(
				Arrays.asList( 4 ), getAuditReader().getRevisions( StrTestNoProxyEntity.class, entity3.getId() )
		);
		assertEquals(
				Arrays.asList( 5, 6 ),
				getAuditReader().getRevisions( StrTestNoProxyEntity.class, entity4.getId() )
		);
	}
