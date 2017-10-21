	private void createData() {
		EntityManager entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();

		Feature newFeature = new Feature();
		newFeature.setName("Feature 1");
		entityManager.persist( newFeature );

		Product product = new Product();
		newFeature.setProduct( product );
		product.getFeatures().add( newFeature );
		entityManager.persist( product );

		entityManager.getTransaction().commit();
		entityManager.clear();

	}
