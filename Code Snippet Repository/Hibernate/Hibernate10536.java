	@Test
	@Priority(7)
	public void testHistoryOfEnums() {
		UnspecifiedEnumTypeEntity ver1 = new UnspecifiedEnumTypeEntity(
				UnspecifiedEnumTypeEntity.E1.X,
				UnspecifiedEnumTypeEntity.E2.A,
				id
		);
		UnspecifiedEnumTypeEntity ver2 = new UnspecifiedEnumTypeEntity(
				UnspecifiedEnumTypeEntity.E1.Y,
				UnspecifiedEnumTypeEntity.E2.B,
				id
		);

		Assert.assertEquals( ver1, getAuditReader().find( UnspecifiedEnumTypeEntity.class, id, 1 ) );
		Assert.assertEquals( ver2, getAuditReader().find( UnspecifiedEnumTypeEntity.class, id, 2 ) );
	}
