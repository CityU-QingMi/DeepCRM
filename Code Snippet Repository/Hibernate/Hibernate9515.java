	@Test
	public void checkSubselectWithFormula() throws Exception {
		// as a pre-condition make sure that subselect fetching is enabled for the collection...
		Collection collectionBinding = metadata().getCollectionBinding( Name.class.getName() + ".values" );
		assertThat( collectionBinding.isSubselectLoadable(), is( true ) );

		// Now force the subselect fetch and make sure we do not get SQL errors
		Session session = openSession();
		session.getTransaction().begin();
		List results = session.createCriteria(Name.class).list();
		for (Object result : results) {
			Name name = (Name) result;
			name.getValues().size();
		}
		session.getTransaction().commit();
		session.close();
	}
