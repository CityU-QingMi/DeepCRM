	@Test
	public void testDeleteState() {
		PropertyNotUpdatableEntity delete = new PropertyNotUpdatableEntity(
				"another modified data",
				"constant data 1",
				"constant data 2",
				id
		);
		List<Object> results = getAuditReader().createQuery().forRevisionsOfEntity(
				PropertyNotUpdatableEntity.class,
				true,
				true
		).getResultList();
		Assert.assertEquals( delete, results.get( 3 ) );
	}
