	@Test
	public void testHistoryOfIngId1() {
		JoinNamingRefIngEntity ver1 = new JoinNamingRefIngEntity( ing_id1, "x", null );
		JoinNamingRefIngEntity ver2 = new JoinNamingRefIngEntity( ing_id1, "y", null );

		assert getAuditReader().find( JoinNamingRefIngEntity.class, ing_id1, 1 ).equals( ver1 );
		assert getAuditReader().find( JoinNamingRefIngEntity.class, ing_id1, 2 ).equals( ver2 );

		assert getAuditReader().find( JoinNamingRefIngEntity.class, ing_id1, 1 ).getReference().equals(
				new JoinNamingRefEdEntity( ed_id1, "data1" )
		);
		assert getAuditReader().find( JoinNamingRefIngEntity.class, ing_id1, 2 ).getReference().equals(
				new JoinNamingRefEdEntity( ed_id2, "data2" )
		);
	}
