	@Test
	public void testNotInsertableEntity() {
		ManyToOneNotInsertableEntity ver1 = getAuditReader().find( ManyToOneNotInsertableEntity.class, mto_id1, 1 );
		ManyToOneNotInsertableEntity ver2 = getAuditReader().find( ManyToOneNotInsertableEntity.class, mto_id1, 2 );
		ManyToOneNotInsertableEntity ver3 = getAuditReader().find( ManyToOneNotInsertableEntity.class, mto_id1, 3 );

		NotInsertableEntityType type1 = getEntityManager().find( NotInsertableEntityType.class, type_id1 );
		NotInsertableEntityType type2 = getEntityManager().find( NotInsertableEntityType.class, type_id2 );

		assert ver1 == null;
		assert ver2.getType().equals( type1 );
		assert ver3.getType().equals( type2 );
	}
