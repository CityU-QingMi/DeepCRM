	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testManyToMany() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Container c = new Container();
		c.setManyToMany( new ArrayList() );
		c.setBag( new ArrayList() );
		Simple s1 = new Simple( Long.valueOf(12) );
		Simple s2 = new Simple( Long.valueOf(-1) );
		s1.setCount(123); s2.setCount(654);
		Contained c1 = new Contained();
		c1.setBag( new ArrayList() );
		c1.getBag().add(c);
		c.getBag().add(c1);
		c.getManyToMany().add(s1);
		c.getManyToMany().add(s2);
		Serializable cid = s.save(c);
		s.save( s1 );
		s.save( s2 );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		c = (Container) s.load(Container.class, cid);
		assertTrue( c.getBag().size()==1 );
		assertTrue( c.getManyToMany().size()==2 );
		c1 = (Contained) c.getBag().iterator().next();
		assertTrue( c.getBag().size()==1 );
		c.getBag().remove(c1);
		c1.getBag().remove(c);
		assertTrue( c.getManyToMany().remove(0)!=null );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		c = (Container) s.load(Container.class, cid);
		assertTrue( c.getBag().size()==0 );
		assertTrue( c.getManyToMany().size()==1 );
		c1 = (Contained) s.load( Contained.class, new Long(c1.getId()) );
		assertTrue( c1.getBag().size()==0 );
		assertEquals( 1, doDelete( s, "from ContainerX c" ) );
		assertEquals( 1, doDelete( s, "from Contained" ) );
		assertEquals( 2, doDelete( s, "from Simple" ) );
		t.commit();
		s.close();
	}
