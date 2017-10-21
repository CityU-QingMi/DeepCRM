	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		LobTestEntity lte = new LobTestEntity( "abc", new byte[]{ 0, 1, 2 }, new char[]{ 'x', 'y', 'z' } );
		em.persist( lte );
		id1 = lte.getId();
		em.getTransaction().commit();

		em.getTransaction().begin();
		lte = em.find( LobTestEntity.class, id1 );
		lte.setStringLob( "def" );
		lte.setByteLob( new byte[]{ 3, 4, 5 } );
		lte.setCharLob( new char[]{ 'h', 'i', 'j' } );
		em.getTransaction().commit();

		// this creates a revision history for a Lob-capable entity but the change is on a non-audited
		// field and so it should only generate 1 revision, the initial persist.
		em.getTransaction().begin();
		LobTestEntity lte2 = new LobTestEntity( "abc", new byte[]{ 0, 1, 2 }, new char[]{ 'x', 'y', 'z' } );
		lte2.setData( "Hi" );
		em.persist( lte2 );
		em.getTransaction().commit();
		id2 = lte2.getId();

		em.getTransaction().begin();
		lte2 = em.find( LobTestEntity.class, id2 );
		lte2.setData( "Hello World" );
		em.getTransaction().commit();
	}
