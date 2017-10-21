	private TypedQuery<OrderLine> getOrderLinesQuery(Collection<PurchaseOrg> purchaseOrgs, EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderLine> query = cb.createQuery( OrderLine.class );
		Root<OrderLine> root = query.from( OrderLine.class );
		Path<OrderLineId> idPath = root.get( OrderLine_.id );

		Join<OrderLine, Product> productJoin = (Join<OrderLine, Product>) root.fetch( OrderLine_.product );
		productJoin.fetch( Product_.facility ).fetch( Facility_.site );

		Join<OrderLine, Order> orderJoin = (Join<OrderLine, Order>) root.fetch( OrderLine_.header );
		orderJoin.fetch( Order_.purchaseOrg );

		Set<Long> ids = new HashSet<>();
		for ( PurchaseOrg org : purchaseOrgs )
			ids.add( org.getId() );

		List<Predicate> predicates = new ArrayList<>();
		predicates.add( idPath.get( OrderLineId_.purchaseOrgId ).in( ids ) );

		query.select( root ).where( predicates.toArray( new Predicate[predicates.size()] ) );

		return em.createQuery( query );
	}
