	@Test
	public void testHistoryOfId() {
		PropertyNotUpdatableEntity ver1 = new PropertyNotUpdatableEntity(
				"data",
				"constant data 1",
				"constant data 2",
				id
		);
		Assert.assertEquals( ver1, getAuditReader().find( PropertyNotUpdatableEntity.class, id, 1 ) );

		PropertyNotUpdatableEntity ver2 = new PropertyNotUpdatableEntity(
				"modified data",
				"constant data 1",
				"constant data 2",
				id
		);
		Assert.assertEquals( ver2, getAuditReader().find( PropertyNotUpdatableEntity.class, id, 2 ) );

		PropertyNotUpdatableEntity ver3 = new PropertyNotUpdatableEntity(
				"another modified data",
				"constant data 1",
				"constant data 2",
				id
		);
		Assert.assertEquals( ver3, getAuditReader().find( PropertyNotUpdatableEntity.class, id, 3 ) );
	}
