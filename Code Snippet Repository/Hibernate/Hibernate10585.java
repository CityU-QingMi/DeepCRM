	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		WikiPage page = new WikiPage( "title", "content" );
		em.persist( page );

		em.getTransaction().commit();

		// Revision 2
		em = getEntityManager();
		em.getTransaction().begin();

		WikiImage image = new WikiImage( "name1" );
		em.persist( image );

		page = em.find( WikiPage.class, page.getId() );
		page.getImages().add( image );

		em.getTransaction().commit();

		// Revision 3
		em = getEntityManager();
		em.getTransaction().begin();

		image = em.find( WikiImage.class, image.getId() );
		image.setName( "name2" );

		em.getTransaction().commit();

		pageId = page.getId();
		imageId = image.getId();
	}
