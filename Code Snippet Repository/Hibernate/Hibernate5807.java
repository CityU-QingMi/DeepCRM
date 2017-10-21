	@Test
	public void testCreateEMFlushMode() throws Exception {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put( "org.hibernate.flushMode", "manual" );
		EntityManager em = createEntityManager( properties );
		em.getTransaction().begin();
		Dress dress = new Dress();
		dress.name = "long dress";
		em.persist( dress );
		em.getTransaction().commit();

		em.clear();

		Assert.assertNull( em.find( Dress.class, dress.name ) );

		em.close();
	}
