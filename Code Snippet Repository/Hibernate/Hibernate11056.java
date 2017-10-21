	@Test
	public void testHistoryOfId1() {
		UnversionedOptimisticLockingFieldEntity ver1 = new UnversionedOptimisticLockingFieldEntity( id1, "x" );
		UnversionedOptimisticLockingFieldEntity ver2 = new UnversionedOptimisticLockingFieldEntity( id1, "y" );

		assert getAuditReader().find( UnversionedOptimisticLockingFieldEntity.class, id1, 1 )
				.equals( ver1 );
		assert getAuditReader().find( UnversionedOptimisticLockingFieldEntity.class, id1, 2 )
				.equals( ver2 );
	}
