	@Test
	public void testWhere() {
		Session s = openSession();
		s.getTransaction().begin();
		File parent = new File("parent", null);
		s.persist( parent );
		s.persist( new File("child", parent) );
		File deletedChild = new File("deleted child", parent);
		deletedChild.setDeleted(true);
		s.persist( deletedChild );
		File deletedParent = new File("deleted parent", null);
		deletedParent.setDeleted(true);
		s.persist( deletedParent );
		s.flush();
		s.clear();
		parent = (File) s.createCriteria(File.class)
				.setFetchMode("children", FetchMode.JOIN)
				.add( Restrictions.isNull("parent") )
				.uniqueResult();
		assertEquals( parent.getChildren().size(), 1 );
		s.clear();
		parent = (File) s.createQuery("from File f left join fetch f.children where f.parent is null")
			.uniqueResult();
		assertEquals( parent.getChildren().size(), 1 );
		s.getTransaction().commit();
		s.close();
	}
