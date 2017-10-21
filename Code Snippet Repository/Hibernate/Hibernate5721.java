	private List<OrderLine> getOrderLinesJpaFetched(Long facilityId) {
		EntityManager em = getOrCreateEntityManager();
		try {
			em.getTransaction().begin();

			Set<PurchaseOrg> purchaseOrgs = getPurchaseOrgsByFacilityId( facilityId, em );
			assertEquals( "Expected one purchase organization.", 1, purchaseOrgs.size() );
			System.out.println( purchaseOrgs );

			TypedQuery<OrderLine> query = getOrderLinesQuery( purchaseOrgs, em );
			List<OrderLine> lines = query.getResultList();
			em.getTransaction().commit();
			return lines;
		}
		catch (Throwable t) {
			if ( em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			throw t;
		}
		finally {
			em.close();
		}
	}
