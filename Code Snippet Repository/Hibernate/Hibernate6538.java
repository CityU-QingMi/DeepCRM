	@Test
	public void testFilterOnCollection() {
		
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		
		Topic topic = new Topic();
		Narrative n1 = new Narrative();
		n1.setState("published");
		topic.addNarrative(n1);
		
		Narrative n2 = new Narrative();
		n2.setState("draft");
		topic.addNarrative(n2);
		
		s.persist(topic);
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		topic = (Topic) s.load( Topic.class, topic.getId() );
		
		s.enableFilter("byState").setParameter("state", "published");
		topic = (Topic) s.load( Topic.class, topic.getId() );
		assertNotNull(topic); 
		assertTrue(topic.getNarratives().size() == 1); 
		assertEquals("published", topic.getNarratives().iterator().next().getState());
		tx.commit();
		s.close();
		
		s = openSession();
		tx = s.beginTransaction();
		s.createQuery( "delete from " + Narrative.class.getSimpleName() ).executeUpdate();
		tx.commit();
		s.close();
	} 
