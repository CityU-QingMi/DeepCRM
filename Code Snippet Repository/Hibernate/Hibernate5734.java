	@Test
	public void allEntities() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MapEntity> query = cb.createQuery(MapEntity.class);

		Root<MapEntity> entity = query.from(MapEntity.class);
		MapJoin<MapEntity, String, MapEntityLocal> cname = entity.join(MapEntity_.localized);
		
		query = query
			.select(entity)
			.where( 
				cb.equal( cname.key(), "en" ) 
			)
			.orderBy( cb.asc( cb.upper( cname.value().get(MapEntityLocal_.shortName) ) ) );
		
		em.createQuery(query).getResultList();
		
		em.getTransaction().commit();
		em.close();
	}
