	@Test
	@TestForIssue(jiraKey = "")
	public void testFunctionCriteria() {
		Wall wall = new Wall();
		wall.setColor( "yellow" );
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( wall );

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wall> query = cb.createQuery( Wall.class );
		Root<Wall> root = query.from( Wall.class );
		
		query.select( root ).where( cb.equal( root.get( "color" ), cb.lower( cb.literal( "YELLOW" ) ) ) );
		
		Wall resultItem = em.createQuery( query ).getSingleResult();
		assertNotNull( resultItem );
		
		em.getTransaction().commit();
		em.close();
	}
