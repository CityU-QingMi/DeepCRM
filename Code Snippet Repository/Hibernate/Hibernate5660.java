	@Test
	@SkipForDialect(value = DerbyDialect.class,comment = "")
	@TestForIssue( jiraKey = "" )
	public void testCastToString() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Product product = new Product();
		product.setId( "product1" );
		product.setPrice( 1.23d );
		product.setQuantity( QUANTITY );
		product.setPartNumber( ((long)Integer.MAX_VALUE) + 1 );
		product.setRating( 1.999f );
		product.setSomeBigInteger( BigInteger.valueOf( 987654321 ) );
		product.setSomeBigDecimal( BigDecimal.valueOf( 987654.321 ) );
		em.persist( product );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery( Product.class );
		Root<Product> root = criteria.from( Product.class );
		criteria.where( builder.equal(root.get(Product_.quantity).as( String.class ), builder.literal(String.valueOf(QUANTITY))) );
		List<Product> result = em.createQuery( criteria ).getResultList();
		Assert.assertEquals( 1, result.size() );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.createQuery( "delete Product" ).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
