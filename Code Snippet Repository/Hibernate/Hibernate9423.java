	@Test
	@TestForIssue(jiraKey = "")
	public void testSortNatural() {
		Session s = openSession();
		s.beginTransaction();
		
		Owner owner = new Owner();
		Cat cat1 = new Cat();
		Cat cat2 = new Cat();
		cat1.owner = owner;
		cat1.name = "B";
		cat2.owner = owner;
		cat2.name = "A";
		owner.cats.add( cat1 );
		owner.cats.add( cat2 );
		s.persist( owner );
		
		s.getTransaction().commit();
		s.clear();
		
		s.beginTransaction();
		
		owner = (Owner) s.get( Owner.class, owner.id );
		assertNotNull(owner.cats);
		assertEquals(owner.cats.size(), 2);
		assertEquals(owner.cats.first().name, "A");
		assertEquals(owner.cats.last().name, "B");
		
		s.getTransaction().commit();
		s.close();
	}
