	@Test
	@TestForIssue(jiraKey = "")
	public void testDeprecation() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {

			Session session = entityManager.unwrap( Session.class );
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Order> query = builder.createQuery( Order.class );
			Root<Order> from = query.from( Order.class );
			query.orderBy( builder.desc( from.get( "totalPrice" ) ) );
			TypedQuery<Order> jpaQuery = session.createQuery( query );
			org.hibernate.query.Query<?> hibQuery = jpaQuery.unwrap( org.hibernate.query.Query.class );

			ScrollableResults sr = hibQuery.scroll( ScrollMode.FORWARD_ONLY );

			hibQuery.setCacheMode( CacheMode.IGNORE ).scroll( ScrollMode.FORWARD_ONLY );

			org.hibernate.query.Query<Order> anotherQuery = session.createQuery(
					"select o from Order o where totalPrice in :totalPrices",
					Order.class
			);
			anotherQuery.setParameterList( "totalPrices", Arrays.asList( 12.5d, 14.6d ) );
		});
	}
