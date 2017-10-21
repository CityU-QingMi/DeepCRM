	@Test
	@TestForIssue( jiraKey = "")
	public void testExplicitJoinBeforeFetchJoins() {

		Session s = openSession();
		s.getTransaction().begin();

		Entity1 e1Queryied =
				(Entity1) s.createQuery(
						"select e1 from Entity1 e1 inner join e1.entity2 e1Restrict inner join fetch e1.entity2 e2 inner join fetch e2.entity3 where e1Restrict.value = 'entity2'" )
						.uniqueResult();
		assertEquals( "entity1", e1Queryied.getValue() );
		assertTrue( Hibernate.isInitialized( e1Queryied.getEntity2() ) );
		assertTrue( Hibernate.isInitialized( e1Queryied.getEntity2().getEntity3() ) );
		s.getTransaction().commit();
		s.close();
	}
