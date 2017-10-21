	@Test
	public void testChildHistory() {
		assert getAuditReader().find( ChildIngEntity.class, c_id, 1 ) == null;
		assert getAuditReader().find( ChildIngEntity.class, c_id, 2 ).getReferenced().equals(
				new ReferencedEntity( re_id1 )
		);
		assert getAuditReader().find( ChildIngEntity.class, c_id, 3 ).getReferenced().equals(
				new ReferencedEntity( re_id2 )
		);
	}
