	@Test
	public void testColumnName() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		Query query = em.createNativeQuery(
				"select nte_data, data_MOD_different from naming_test_entity_2_versions where nte_id = :nteId");
		query.setParameter("nteId", this.id);
		List<Object[]> resultList = query.getResultList();
		Assert.assertNotNull(resultList);
		Assert.assertTrue(resultList.size() > 0);
		Object[] result = resultList.get(0);
		Assert.assertEquals(result.length, 2);
		em.getTransaction().commit();
	}
