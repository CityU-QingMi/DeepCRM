	@After
	public void cleanup() {
		b.setC( null );
		b.setD( null );
		b.getGCollection().remove( g );

		c.getBCollection().remove( b );
		c.getDCollection().remove( d );

		d.getBCollection().remove( b );
		d.setC( null );
		d.setE( null );
		d.getFCollection().remove( f );

		e.getDCollection().remove( d );
		e.setF( null );

		f.setD( null );
		f.getECollection().remove( e );
		f.setG( null );

		g.setB( null );
		g.getFCollection().remove( f );

		Session s = openSession();
		s.getTransaction().begin();
		b = (EntityB) s.merge( b );
		c = (EntityC) s.merge( c );
		d = (EntityD) s.merge( d );
		e = (EntityE) s.merge( e );
		f = (EntityF) s.merge( f );
		g = (EntityG) s.merge( g );
		s.delete( f );
		s.delete( g );
		s.delete( b );
		s.delete( d );
		s.delete( e );
		s.delete( c );
		s.getTransaction().commit();
		s.close();
	}
