	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// revision 1
		em.getTransaction().begin();
		address1 = new Address( "Freiburgerstrasse", 5 );
		em.persist( address1 );
		address2 = new Address( "Hindenburgstrasse", 30 );
		em.persist( address2 );
		vwOwner = new Person( "VW owner", 20, address1 );
		em.persist( vwOwner );
		fordOwner = new Person( "Ford owner", 30, address1 );
		em.persist( fordOwner );
		toyotaOwner = new Person( "Toyota owner", 30, address2 );
		em.persist( toyotaOwner );
		final Person nonOwner = new Person( "NonOwner", 30, address1 );
		em.persist( nonOwner );
		vw = new Car( "VW" );
		vw.setOwner( vwOwner );
		em.persist( vw );
		ford = new Car( "Ford" );
		ford.setOwner( fordOwner );
		em.persist( ford );
		toyota = new Car( "Toyota" );
		toyota.setOwner( toyotaOwner );
		em.persist( toyota );
		em.getTransaction().commit();

		// revision 2
		em.getTransaction().begin();
		toyotaOwner.setAge( 40 );
		em.getTransaction().commit();
	}
