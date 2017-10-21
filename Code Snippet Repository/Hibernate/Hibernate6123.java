	@Test
	@SkipForDialect(value = SybaseDialect.class, comment = "")
	public void testNullPositionalParameterParameterIncompatible() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Item item = new Item( "Mouse", "Micro$oft mouse" );
			em.persist( item );
			Query q = em.createQuery( "from Item i where i.intVal=?" );
			Parameter p = new Parameter() {
				@Override
				public String getName() {
					return null;
				}

				@Override
				public Integer getPosition() {
					return 0;
				}

				@Override
				public Class getParameterType() {
					return Long.class;
				}
			};
			q.setParameter( p, null );
			Parameter pGotten = q.getParameter( p.getPosition() );
			List results = q.getResultList();
			// null != null
			assertEquals( 0, results.size() );
			q = em.createQuery( "from Item i where i.intVal is null and ? is null" );
			q.setParameter( p, null );
			results = q.getResultList();
			assertEquals( 1, results.size() );
			q = em.createQuery( "from Item i where i.intVal is null or i.intVal = ?" );
			q.setParameter( p, null );
			results = q.getResultList();
			assertEquals( 1, results.size() );
		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}
