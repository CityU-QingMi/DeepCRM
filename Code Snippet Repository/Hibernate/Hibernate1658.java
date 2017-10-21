	private EntityMode getEntityMode(Criteria criteria, CriteriaQuery criteriaQuery) {
		final EntityPersister meta = criteriaQuery.getFactory().getEntityPersister(
				criteriaQuery.getEntityName( criteria )
		);
		final EntityMode result = meta.getEntityMode();
		if ( ! meta.getEntityMetamodel().getTuplizer().isInstance( exampleEntity ) ) {
			throw new ClassCastException( exampleEntity.getClass().getName() );
		}
		return result;
	}
