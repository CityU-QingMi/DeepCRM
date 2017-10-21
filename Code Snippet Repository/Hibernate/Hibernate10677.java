	@Test
	public void testHistoryOfReferencedCollection() {
		assert getAuditReader().find( ReferencedEntity.class, ed_id1, 1 ).getReferencing().size() == 0;
		assert getAuditReader().find( ReferencedEntity.class, ed_id1, 2 ).getReferencing().equals(
				TestTools.makeSet( new ParentIngEntity( p_id, "x" ) )
		);
		assert getAuditReader().find( ReferencedEntity.class, ed_id1, 3 ).getReferencing().equals(
				TestTools.makeSet( new ParentIngEntity( p_id, "x" ), new ChildIngEntity( c_id, "y", 1l ) )
		);
	}
