	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		Person lukasz = new Person();
		lukasz.setName( "Lukasz" );
		lukasz.setGroup( "IT" );
		em.persist( lukasz );
		Role admin = new Role();
		admin.setName( "Admin" );
		admin.setGroup( "Confidential" );
		lukasz.getRoles().add( admin );
		admin.getMembers().add( lukasz );
		em.persist( admin );
		em.getTransaction().commit();

		expLukaszRev1 = new Person( lukasz.getId(), "IT", "Lukasz" );
		expAdminRev1 = new Role( admin.getId(), "Confidential", "Admin" );
	}
