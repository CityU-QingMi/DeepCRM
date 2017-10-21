	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1 - simple entity
		em.getTransaction().begin();
		em.persist( new StrTestEntity( "data" ) );
		em.getTransaction().commit();

		// Revision 2 - many-to-many relation
		em.getTransaction().begin();
		ListOwnedEntity owned = new ListOwnedEntity( 1, "data" );
		ListOwningEntity owning = new ListOwningEntity( 1, "data" );
		owned.setReferencing( new ArrayList<ListOwningEntity>() );
		owning.setReferences( new ArrayList<ListOwnedEntity>() );
		owned.getReferencing().add( owning );
		owning.getReferences().add( owned );
		em.persist( owned );
		em.persist( owning );
		em.getTransaction().commit();

		em.getTransaction().begin();
		Assert.assertEquals( 1, countRecords( em, "STR_TEST_AUD" ) );
		Assert.assertEquals( 1, countRecords( em, "ListOwned_AUD" ) );
		Assert.assertEquals( 1, countRecords( em, "ListOwning_AUD" ) );
		Assert.assertEquals( 1, countRecords( em, "ListOwning_ListOwned_AUD" ) );
		em.getTransaction().commit();

		em.close();
	}
