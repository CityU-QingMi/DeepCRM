	@Test
	@TestForIssue( jiraKey = "" )
	public void testFlushOnDetached() throws Exception {
		EntityManager manager = getOrCreateEntityManager( );

		manager.getTransaction().begin();
		Pet p1 = createCat("Toonses", 15.0, 9, manager);
		manager.flush();
		manager.clear();

		Pet p2 = createCat("Sox", 10.0, 5, manager);
		manager.flush();
		manager.clear();

		Pet p3 = createDog("Winnie", 70.0, 5, manager);
		manager.flush();
		manager.clear();

		Pet p4 = createDog("Junior", 11.0, 1, manager);
		manager.flush();
		manager.clear();

		Decorate d1 = createDecorate("Test", p1, manager);
		manager.flush();
		manager.clear();

		Decorate d2 = createDecorate("Test2", p2, manager);
		manager.flush();
		manager.clear();

		List l = findByWeight(14.0, manager);
		manager.flush();
		manager.clear();
		for (Object o : l) {
			Assert.assertTrue( names.contains( ((Pet) o).getName() ) );
		}

		Collection<Decorate> founds = getDecorate(manager);
		manager.flush();
		manager.clear();
		for (Decorate value : founds) {
			Assert.assertTrue( names.contains( value.getPet().getName() ) );
		}
		manager.getTransaction().rollback();
		
		manager.close();
	}
