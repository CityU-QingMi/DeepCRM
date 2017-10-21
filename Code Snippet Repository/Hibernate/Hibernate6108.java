	@Test
	public void testPositionalParameterForms() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Wallet w = new Wallet();
			w.setBrand( "Lacoste" );
			w.setModel( "Minimic" );
			w.setSerial( "0100202002" );
			em.persist( w );
			em.getTransaction().commit();

			em.getTransaction().begin();
			// first using jpa-style positional parameter
			Query query = em.createQuery( "select w from Wallet w where w.brand = ?1" );
			query.setParameter( 1, "Lacoste" );
			w = (Wallet) query.getSingleResult();
			assertNotNull( w );

			// next using jpa-style positional parameter, but as a name (which is how Hibernate core treats these
			query = em.createQuery( "select w from Wallet w where w.brand = ?1" );
			// deprecated usage of positional parameter by String
			query.setParameter( "1", "Lacoste" );
			w = (Wallet) query.getSingleResult();
			assertNotNull( w );

			// finally using hql-style positional parameter
			query = em.createQuery( "select w from Wallet w where w.brand = ?" );
			query.setParameter( 0, "Lacoste" );
			w = (Wallet) query.getSingleResult();
			assertNotNull( w );

			em.remove( w );
			em.getTransaction().commit();
		}
		catch (Exception e){
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			em.close();
		}
	}
