	@Test
	public void testHistoryOfIngId1() {
		JoinEmbIdNamingRefIngEntity ver1 = new JoinEmbIdNamingRefIngEntity( ing_id1, "x", null );
		JoinEmbIdNamingRefIngEntity ver2 = new JoinEmbIdNamingRefIngEntity( ing_id1, "y", null );

		assert getAuditReader().find( JoinEmbIdNamingRefIngEntity.class, ing_id1, 1 ).equals( ver1 );
		assert getAuditReader().find( JoinEmbIdNamingRefIngEntity.class, ing_id1, 2 ).equals( ver2 );

		assert getAuditReader().find( JoinEmbIdNamingRefIngEntity.class, ing_id1, 1 ).getReference().equals(
				new JoinEmbIdNamingRefEdEntity( ed_id1, "data1" )
		);
		assert getAuditReader().find( JoinEmbIdNamingRefIngEntity.class, ing_id1, 2 ).getReference().equals(
				new JoinEmbIdNamingRefEdEntity( ed_id2, "data2" )
		);
	}
