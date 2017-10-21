	@Test
	public void testHistoryOfIngId1() {
		JoinMulIdNamingRefIngEntity ver1 = new JoinMulIdNamingRefIngEntity( ing_id1, "x", null );
		JoinMulIdNamingRefIngEntity ver2 = new JoinMulIdNamingRefIngEntity( ing_id1, "y", null );

		assert getAuditReader().find( JoinMulIdNamingRefIngEntity.class, ing_id1, 1 ).equals( ver1 );
		assert getAuditReader().find( JoinMulIdNamingRefIngEntity.class, ing_id1, 2 ).equals( ver2 );

		assert getAuditReader().find( JoinMulIdNamingRefIngEntity.class, ing_id1, 1 ).getReference().equals(
				new JoinMulIdNamingRefEdEntity( ed_id1, "data1" )
		);
		assert getAuditReader().find( JoinMulIdNamingRefIngEntity.class, ing_id1, 2 ).getReference().equals(
				new JoinMulIdNamingRefEdEntity( ed_id2, "data2" )
		);
	}
