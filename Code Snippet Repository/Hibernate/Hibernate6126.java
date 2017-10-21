	@Test
	@SkipForDialect(value = SybaseDialect.class, comment = "")
	public void testNullNamedParameterParameterIncompatible() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Item item = new Item( "Mouse", "Micro$oft mouse" );
			em.persist( item );
			Query q = em.createQuery( "from Item i where i.intVal=:iVal" );
			Parameter p = new Parameter() {
				@Override
				public String getName() {
					return "iVal";
				}

				@Override
				public Integer getPosition() {
					return null;
				}

				@Override
				public Class getParameterType() {
					return Long.class;
				}
			};
			q.setParameter( p, null );
			List results = q.getResultList();
			// null != null
			assertEquals( 0, results.size() );
			q = em.createQuery( "from Item i where i.intVal is null and :iVal is null" );
			q.setParameter( p, null );
			results = q.getResultList();
			assertEquals( 1, results.size() );
			q = em.createQuery( "from Item i where i.intVal is null or i.intVal = :iVal" );
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
