	@Test
	@TestForIssue(jiraKey = "")
	public void testParameterInParameterList2() {
		TransactionUtil.doInJPA( this::entityManagerFactory, em -> {
			final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			final CriteriaQuery<MultiTypedBasicAttributesEntity> criteria = criteriaBuilder
					.createQuery( MultiTypedBasicAttributesEntity.class );

			final ParameterExpression<Iterable> parameter = criteriaBuilder.parameter( Iterable.class );

			final Root<MultiTypedBasicAttributesEntity> root = criteria.from( MultiTypedBasicAttributesEntity.class );
			criteria.select( root ).where( root.get( "id" ).in( parameter ) );

			final TypedQuery<MultiTypedBasicAttributesEntity> query1 = em.createQuery( criteria );
			query1.setParameter( parameter, Arrays.asList( 1L, 2L, 3L ) );
			query1.getResultList();
		} );
	}
