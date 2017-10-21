	@Test
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		LobSerializableTestEntity ste = new LobSerializableTestEntity( new SerObject( "d1" ) );
		em.persist( ste );
		id1 = ste.getId();
		em.getTransaction().commit();

		em.getTransaction().begin();
		ste = em.find( LobSerializableTestEntity.class, id1 );
		ste.setObj( new SerObject( "d2" ) );
		em.getTransaction().commit();
	}
