	@Test
	public void testJoinedElementCollectionValuesInTupleList() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Phone> criteria = builder.createQuery( Phone.class );
		Root<Phone> from = criteria.from( Phone.class );
		criteria.where(
				from.join( "types" )
						.in( Collections.singletonList( Phone.Type.WORK ) )
		);
		em.createQuery( criteria ).getResultList();
		em.getTransaction().commit();
		em.close();
	}
