	public void testPersistThenUpdate() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( b );
		// remove old e from associations
		e.getDCollection().remove( d );
		d.setE( null );
		f.getECollection().remove( e );
		e.setF( null );
		// add new e to associations
		e = new E();
		e.getDCollection().add( d );
		f.getECollection().add( e );
		d.setE( e );
		e.setF( f );
		em.getTransaction().commit();
		em.close();

		check();
	}
