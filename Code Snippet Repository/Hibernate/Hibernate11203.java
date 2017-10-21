	@Test
	@Priority(8)
	public void testEmptyAuditTables() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		Assert.assertEquals( 0, countRecords( em, "STR_TEST_AUD" ) );
		Assert.assertEquals( 0, countRecords( em, "ListOwned_AUD" ) );
		Assert.assertEquals( 0, countRecords( em, "ListOwning_AUD" ) );
		Assert.assertEquals( 0, countRecords( em, "ListOwning_ListOwned_AUD" ) );

		em.getTransaction().commit();
		em.close();
	}
