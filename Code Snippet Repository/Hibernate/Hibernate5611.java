	@SkipForDialect(MySQLDialect.class)
	public void testDeleteWithUnCorrelatedSubquery() {
		CriteriaBuilder builder = entityManagerFactory().getCriteriaBuilder();
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		// attempt to delete Customers who's age is less than the AVG age
		CriteriaDelete<Customer> criteria = builder.createCriteriaDelete( Customer.class );
		Root<Customer> customerRoot = criteria.from( Customer.class );

		Subquery<Double> subCriteria = criteria.subquery( Double.class );
		Root<Customer> subQueryCustomerRoot = subCriteria.from( Customer.class );
		subCriteria.select( builder.avg( subQueryCustomerRoot.get( Customer_.age ) ) );

		// also illustrates the new capability to use the subquery selection as an expression!
		criteria.where(
				builder.lessThan(
						customerRoot.get( Customer_.age ),
						subCriteria.getSelection().as( Integer.class )
				)
		);

		// make sure Subquery#getParent fails...
		try {
			subCriteria.getParent();
			fail( "Expecting Subquery.getParent call to fail on DELETE containing criteria" );
		}
		catch (IllegalStateException expected) {
		}

		Query query = em.createQuery( criteria );
		try {
			// first, make sure an attempt to list fails
			query.getResultList();
			fail( "Attempt to getResultList() on delete criteria should have failed" );
		}
		catch (IllegalStateException expected) {
		}
		query.executeUpdate();

		em.getTransaction().commit();
		em.close();
	}
