	@Test
	@TestForIssue( jiraKey = "")
	public void testManyToOneInCompositePkInPC() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		ParentPk ppk = new ParentPk();
		ppk.setFirstName( "Emmanuel" );
		ppk.setLastName( "Bernard" );
		Parent p = new Parent();
		p.id = ppk;
		s.persist( p );
		ChildPk cpk = new ChildPk();
		cpk.parent = p;
		cpk.nthChild = 1;
		Child c = new Child();
		c.id = cpk;
		s.persist( c );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		p = (Parent) s.get( Parent.class, ppk);
		// p.id should be ppk.
		assertSame( ppk, p.id );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		c = (Child) s.get( Child.class, cpk );
		// c.id should be cpk
		assertSame( cpk, c.id );
		// only Child should be in PC (c.id.parent should not be in PC)
		SessionImplementor sessionImplementor = (SessionImplementor) s;
		assertTrue( sessionImplementor.getPersistenceContext().isEntryFor( c ) );
		assertFalse( sessionImplementor.getPersistenceContext().isEntryFor( c.id.parent ) );
		tx.commit();
		s.close();
	}
