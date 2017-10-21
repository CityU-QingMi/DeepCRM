	private void createData() {
		Session s = openSession();
		s.getTransaction().begin();

		Feature newFeature = new Feature();
		newFeature.setName("Feature 1");
		s.persist( newFeature );

		Product product = new Product();
		newFeature.setProduct( product );
		product.getFeatures().add( newFeature );
		s.persist( product );

		s.getTransaction().commit();
		s.clear();

	}
