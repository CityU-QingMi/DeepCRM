	@Test
	public void testTemporalTypeBinding() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Query query = em.createQuery( "select w from " + Wallet.class.getName() + " w where w.marketEntrance = :me" );
			Parameter parameter = query.getParameter( "me", Date.class );
			assertEquals( parameter.getParameterType(), Date.class );

			query.setParameter( "me", new Date() );
			query.setParameter( "me", new Date(), TemporalType.DATE );
			query.setParameter( "me", new GregorianCalendar(), TemporalType.DATE );

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
