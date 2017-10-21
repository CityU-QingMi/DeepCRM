	@Test
	public void testAliases() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Animal a = new Animal();
		a.setBodyWeight(12.4f);
		a.setDescription("an animal");
		s.persist(a);
		String[] aliases1 = s.createQuery("select a.bodyWeight as abw, a.description from Animal a").getReturnAliases();
		assertEquals( "abw", aliases1[0] );
		assertEquals( null, aliases1[1] );
		String[] aliases2 = s.createQuery("select count(*), avg(a.bodyWeight) as avg from Animal a").getReturnAliases();
		assertEquals( null, aliases2[0] );
		assertEquals( "avg", aliases2[1] );
		s.delete(a);
		t.commit();
		s.close();
	}
