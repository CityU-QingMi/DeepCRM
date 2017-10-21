	@Test
	public void testCustomSQL() throws Exception {
		Cat cat = new Cat();
		String storyPart2 = "My long story";
		cat.setStoryPart2( storyPart2 );
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		s.persist( cat );
		s.flush();
		s.clear();

		Cat c = (Cat) s.get( Cat.class, cat.getId() );
		assertEquals( storyPart2.toUpperCase(Locale.ROOT), c.getStoryPart2() );

		tx.rollback();
		s.close();
	}
