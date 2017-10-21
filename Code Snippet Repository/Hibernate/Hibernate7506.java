	private MapEntity persist(MapEntity mapEntity) {
		Transaction tx = openSession().getTransaction();
		tx.begin();
		mapEntity = (MapEntity) getSession().merge( mapEntity );

		tx.commit();
		getSession().close();

		mapEntity = openSession().get( MapEntity.class, mapEntity.id );
		return mapEntity;
	}
