	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		Parent parent = new Parent( "parent" );
		Child child = new Child( "child" );
		parent.getCollection().add( child );
		em.persist( child );
		em.persist( parent );
		em.getTransaction().commit();

		parentId = parent.getId();
		childId = child.getId();
	}
