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
		b = ( B ) s.merge( b );
		c = ( C ) s.merge( c );
		d = ( D ) s.merge( d );
		e = ( E ) s.merge( e );
		f = ( F ) s.merge( f );
		g = ( G ) s.merge( g );
		s.delete( f );
		s.delete( g );
		s.delete( b );
		s.delete( d );
		s.delete( e );
		s.delete( c );
		s.getTransaction().commit();
		s.close();
	}
