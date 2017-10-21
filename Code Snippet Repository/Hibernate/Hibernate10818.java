	private Integer getCurrentAuditUniqueGroupId() {
		return TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Session session = entityManager.unwrap( Session.class );
			final Query query = session.createSQLQuery(
					"SELECT uniqueGroup_id FROM GroupMember_AUD ORDER BY rev DESC" ).addScalar(
					"uniqueGroup_id",
					IntegerType.INSTANCE
			).setMaxResults( 1 );
			final Object result = query.getSingleResult();
			assertNotNull( result );
			return (Integer) result;
		} );
	}
