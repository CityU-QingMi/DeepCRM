	@After
	public void cleanup() {
		if ( ! skipCleanup ) {
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

			EntityManager em = getOrCreateEntityManager();
			em.getTransaction().begin();
			b = em.merge( b );
			c = em.merge( c );
			d = em.merge( d );
			e = em.merge( e );
			f = em.merge( f );
			g = em.merge( g );
			em.remove( f );
			em.remove( g );
			em.remove( b );
			em.remove( d );
			em.remove( e );
			em.remove( c );
			em.getTransaction().commit();
			em.close();
		}
	}
