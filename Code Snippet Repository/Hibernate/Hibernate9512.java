	@Test
	public void testManyToManyCriteriaJoin() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Parent p = new Parent("foo");
		p.getChildren().add( new Child("foo1") );
		p.getChildren().add( new Child("foo2") );
		Parent q = new Parent("bar");
		q.getChildren().add( new Child("bar1") );
		q.getChildren().add( new Child("bar2") );
		q.getMoreChildren().addAll( p.getChildren() );
		s.persist(p); 
		s.persist(q);
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		
		List parents = s.createCriteria(Parent.class)
			.createCriteria("moreChildren")
			.createCriteria("friends")
			.addOrder( Order.desc("name") )
			.list();

		parents = s.createCriteria(Parent.class)
			.setFetchMode("moreChildren", FetchMode.JOIN)
			.setFetchMode("moreChildren.friends", FetchMode.JOIN)
			.addOrder( Order.desc("name") )
			.list();

		s.delete( parents.get(0) );
		s.delete( parents.get(1) );

		t.commit();
		s.close();
	}
