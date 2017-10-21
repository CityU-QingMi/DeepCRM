	private List<OrderLine> getOrderLinesScrolled(Long facilityId) {
		EntityManager em = getOrCreateEntityManager();
		try {
			em.getTransaction().begin();

			Set<PurchaseOrg> purchaseOrgs = getPurchaseOrgsByFacilityId( facilityId, em );
			assertEquals( "Expected one purchase organization.", 1, purchaseOrgs.size() );
			System.out.println( purchaseOrgs );

			TypedQuery<OrderLine> query = getOrderLinesQuery( purchaseOrgs, em );

			Query hibernateQuery = query.unwrap( Query.class );
			hibernateQuery.setReadOnly( true );
			hibernateQuery.setCacheable( false );

			List<OrderLine> lines = new ArrayList<>();
			ScrollableResults scrollableResults = hibernateQuery.scroll();
			scrollableResults.last();
			int rows = scrollableResults.getRowNumber() + 1;
			scrollableResults.beforeFirst();
			while ( scrollableResults.next() ) {
				lines.add( (OrderLine) scrollableResults.get( 0 ) );
			}
			assertNotNull( lines );
			assertEquals( "Expected one order line", 1, lines.size() );

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
