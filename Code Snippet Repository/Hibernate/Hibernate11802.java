	@Test
	public void shouldUseHibernateOrm52() {
		Session session = entityManager.unwrap( Session.class );

		Kryptonite kryptonite1 = new Kryptonite();
		kryptonite1.id = 1L;
		kryptonite1.description = "Some Kryptonite";
		session.persist( kryptonite1 );

		// EntityManager methods exposed through Session only as of 5.2
		Kryptonite loaded = session.find( Kryptonite.class, 1L );

		assertThat( loaded.description, equalTo( "Some Kryptonite" ) );
	}
