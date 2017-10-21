	@Test
	public void testQueryCollectionOfValues() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		baz.setDefaults();
		s.save(baz);
		Glarch g = new Glarch();
		Serializable gid = s.save(g);

		if ( !(getDialect() instanceof MySQLDialect) && !(getDialect() instanceof HSQLDialect) /*&& !(dialect instanceof MckoiDialect)*/ && !(getDialect() instanceof SAPDBDialect) && !(getDialect() instanceof PointbaseDialect) && !(getDialect() instanceof TimesTenDialect) ) {
			s.createFilter( baz.getFooArray(), "where size(this.bytes) > 0" ).list();
			s.createFilter( baz.getFooArray(), "where 0 in elements(this.bytes)" ).list();
		}
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.createQuery( "from Baz baz join baz.fooSet foo join foo.foo.foo foo2 where foo2.string = 'foo'" ).list();
		s.createQuery( "from Baz baz join baz.fooArray foo join foo.foo.foo foo2 where foo2.string = 'foo'" ).list();
		s.createQuery( "from Baz baz join baz.stringDateMap date where index(date) = 'foo'" ).list();
		s.createQuery( "from Baz baz join baz.topGlarchez g where index(g) = 'A'" ).list();
		s.createQuery( "select index(g) from Baz baz join baz.topGlarchez g" ).list();

		assertTrue( s.createQuery( "from Baz baz left join baz.stringSet" ).list().size()==3 );
		baz = (Baz) s.createQuery( "from Baz baz join baz.stringSet str where str='foo'" ).list().get(0);
		assertTrue( !Hibernate.isInitialized( baz.getStringSet() ) );
		baz = (Baz) s.createQuery( "from Baz baz left join fetch baz.stringSet" ).list().get(0);
		assertTrue( Hibernate.isInitialized( baz.getStringSet() ) );
		assertTrue( s.createQuery( "from Baz baz join baz.stringSet string where string='foo'" ).list().size()==1 );
		assertTrue( s.createQuery( "from Baz baz inner join baz.components comp where comp.name='foo'" ).list().size()==1 );
		//List bss = s.find("select baz, ss from Baz baz inner join baz.stringSet ss");
		s.createQuery( "from Glarch g inner join g.fooComponents comp where comp.fee is not null" ).list();
		s.createQuery( "from Glarch g inner join g.fooComponents comp join comp.fee fee where fee.count > 0" ).list();
		s.createQuery( "from Glarch g inner join g.fooComponents comp where comp.fee.count is not null" ).list();

		s.delete(baz);
		s.delete( s.get(Glarch.class, gid) );
		s.getTransaction().commit();
		s.close();
	}
