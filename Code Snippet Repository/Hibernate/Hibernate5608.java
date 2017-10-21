	@Test
	public void basicTest() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		{
			CriteriaDelete<Customer> deleteCriteria = builder.createCriteriaDelete( Customer.class );
			deleteCriteria.from( Customer.class );
			em.createQuery( deleteCriteria ).executeUpdate();
		}

		{
			CriteriaDelete<Customer> deleteCriteria = builder.createCriteriaDelete( Customer.class );
			Root<Customer> root = deleteCriteria.from( Customer.class );
			deleteCriteria.where(
					builder.equal(
							root.get( Customer_.name ),
							"Acme"
					)
			);
			em.createQuery( deleteCriteria ).executeUpdate();
		}

		{
			CriteriaUpdate<Customer> updateCriteria = builder.createCriteriaUpdate( Customer.class );
			updateCriteria.from( Customer.class );
			updateCriteria.set( Customer_.name, "Acme" );
			em.createQuery( updateCriteria ).executeUpdate();
		}

		{
			CriteriaUpdate<Customer> updateCriteria = builder.createCriteriaUpdate( Customer.class );
			Root<Customer> root = updateCriteria.from( Customer.class );
			updateCriteria.set( Customer_.name, "Acme" );
			updateCriteria.where(
					builder.equal(
							root.get( Customer_.name ),
							"Acme"
					)
			);
			em.createQuery( updateCriteria ).executeUpdate();
		}

		em.getTransaction().commit();
		em.close();
	}
