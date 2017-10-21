	@Override
	protected void prepareTest() throws Exception {
		final Session s = openSession();
		s.getTransaction().begin();
		Product product = new Product();
		product.setPrice(new BigDecimal(0.5));
		product.setDate( calendar.getTime() );
		s.save( product );
		s.getTransaction().commit();
		s.close();
	}
