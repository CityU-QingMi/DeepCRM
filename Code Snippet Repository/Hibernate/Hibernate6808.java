	@Test
	public void testCompositeFK() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		ParentPk ppk = new ParentPk();
		ppk.firstName = "John";
		ppk.lastName = "Doe";
		Parent p = new Parent();
		p.age = 45;
		p.id = ppk;
		s.persist( p );
		Child c = new Child();
		c.parent = p;
		s.persist( c );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		//FIXME: fix this when the small parser bug will be fixed
		Query q = s.createQuery( "from " + Child.class.getName() ); //+ " c where c.parent.id.lastName = :lastName");
		//q.setString("lastName", p.id.lastName);
		List result = q.list();
		assertEquals( 1, result.size() );
		Child c2 = (Child) result.get( 0 );
		assertEquals( c2.id, c.id );
		tx.commit();
		s.close();
	}
