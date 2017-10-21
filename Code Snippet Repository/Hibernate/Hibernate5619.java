	@Test
	public void testEqualityComparisonLiteralConversion() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaBuilderImpl cb = (CriteriaBuilderImpl) em.getCriteriaBuilder();
		MetamodelImpl mm = (MetamodelImpl) em.getMetamodel();

        CriteriaQuery<Integer> cquery = cb.createQuery( Integer.class );
		Root<Product> product = cquery.from( Product.class );
		EntityType<Product> Product_ = mm.entity( Product.class );

		cquery.select(
				cb.toInteger(
						product.get(
								Product_.getSingularAttribute("quantity", Integer.class))
				)
		);

		ComparisonPredicate predicate = (ComparisonPredicate) cb.equal(
				product.get( Product_.getSingularAttribute( "partNumber", Long.class ) ),
				373767373
		);
		assertEquals( Long.class, predicate.getRightHandOperand().getJavaType() );
		cquery.where( predicate );
		em.createQuery( cquery ).getResultList();

		predicate = (ComparisonPredicate) cb.ge(
				cb.length( product.get( Product_.getSingularAttribute( "name", String.class ) ) ),
				4L
		);
		assertEquals( Integer.class, predicate.getRightHandOperand().getJavaType() );
		cquery.where( predicate );
		em.createQuery( cquery ).getResultList();

		em.getTransaction().commit();
		em.close();
	}
