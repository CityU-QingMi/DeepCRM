	@After
	public void cleanup() {
		Session s = openSession();
		s.getTransaction().begin();
		Parent parent = s.get( Parent.class, parentId );
		parent.getChildren().clear();
		s.delete( parent );
		s.getTransaction().commit();
		s.close();

		parentId = null;
	}
