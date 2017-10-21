	@Test
	public void testMapKeyLoad() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Software hibernate = new Software();
		hibernate.setName( "Hibernate" );
		Version v1 = new Version();
		v1.setCodeName( "HumbaHumba" );
		v1.setNumber( "1.0" );
		v1.setSoftware( hibernate );
		hibernate.addVersion( v1 );
		s.persist( hibernate );
		s.persist( v1 );

		s.flush();
		s.clear();

		hibernate = (Software) s.get( Software.class, "Hibernate" );
		assertEquals(1, hibernate.getVersions().size() );
		Version v2 = new Version();
		v2.setCodeName( "HumbaHumba2" );
		v2.setNumber( "2.0" );
		v2.setSoftware( hibernate );
		hibernate.addVersion( v2 );
		assertEquals( "One loaded persisted version, and one just added", 2, hibernate.getVersions().size() );

		s.flush();
		s.clear();

		hibernate = (Software) s.get( Software.class, "Hibernate" );
		for ( Version v : hibernate.getVersions().values() ) {
			s.delete( v );
		}
		s.delete( hibernate );
		tx.rollback();
		s.close();
	}
