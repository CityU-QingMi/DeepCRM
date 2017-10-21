	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testCascadeCompositeElements() throws Exception {
		Container c = new Container();
		List list = new ArrayList();
		c.setCascades(list);
		Container.ContainerInnerClass cic = new Container.ContainerInnerClass();
		cic.setMany( new Many() );
		cic.setOne( new One() );
		list.add(cic);
		Session s = openSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
		s.close();
		
		s=openSession();
		s.beginTransaction();
		c = (Container) s.createQuery( "from ContainerX c" ).iterate().next();
		cic = (Container.ContainerInnerClass) c.getCascades().iterator().next();
		assertTrue( cic.getMany()!=null && cic.getOne()!=null );
		assertTrue( c.getCascades().size()==1 );
		s.delete(c);
		s.getTransaction().commit();
		s.close();

		c = new Container();
		s = openSession();
		s.beginTransaction();
		s.save(c);
		list = new ArrayList();
		c.setCascades(list);
		cic = new Container.ContainerInnerClass();
		cic.setMany( new Many() );
		cic.setOne( new One() );
		list.add(cic);
		s.getTransaction().commit();
		s.close();
		
		s=openSession();
		s.beginTransaction();
		c = (Container) s.createQuery( "from ContainerX c" ).iterate().next();
		cic = (Container.ContainerInnerClass) c.getCascades().iterator().next();
		assertTrue( cic.getMany()!=null && cic.getOne()!=null );
		assertTrue( c.getCascades().size()==1 );
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}
