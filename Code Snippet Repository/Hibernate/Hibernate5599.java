	@Test
	public void testTrim() {
		final String expectedResult = "David R. Vincent";

		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();

			CriteriaQuery<String> cquery = cb.createQuery( String.class );
			Root<Customer> cust = cquery.from( Customer.class );

			//Get Metamodel from Root
			EntityType<Customer> Customer_ = cust.getModel();

			cquery.where(
					cb.equal(
							cust.get( Customer_.getSingularAttribute( "name", String.class ) ),
							cb.literal( " David R. Vincent " )
					)
			);
			cquery.select(
					cb.trim(
							CriteriaBuilder.Trimspec.BOTH,
							cust.get( Customer_.getSingularAttribute( "name", String.class ) )
					)
			);

			TypedQuery<String> tq = entityManager.createQuery( cquery );

			String result = tq.getSingleResult();
			Assert.assertEquals( "Mismatch in received results", expectedResult, result );
		} );
	}
