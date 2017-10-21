	@Test
	public void testTypeConversion() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaBuilderImpl cb = (CriteriaBuilderImpl) em.getCriteriaBuilder();
		MetamodelImpl mm = (MetamodelImpl) em.getMetamodel();
		EntityType<Product> Product_ = mm.entity( Product.class );

		// toFloat
        CriteriaQuery<Float> floatQuery = cb.createQuery( Float.class );
		Root<Product> product = floatQuery.from( Product.class );
		floatQuery.select(
				cb.toFloat(
						product.get(Product_.getSingularAttribute("quantity", Integer.class))
				)
		);
		em.createQuery( floatQuery ).getResultList();

		// toDouble
        CriteriaQuery<Double> doubleQuery = cb.createQuery(Double.class);
		product = doubleQuery.from( Product.class );
		doubleQuery.select(
				cb.toDouble(
						product.get(Product_.getSingularAttribute("quantity", Integer.class))
				)
		);
		em.createQuery( doubleQuery ).getResultList();

		em.getTransaction().commit();
		em.close();
	}
