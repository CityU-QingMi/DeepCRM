	@Override
	@SuppressWarnings({"", ""})
	public <T> List<T> loadAll(final Class<T> entityClass) throws DataAccessException {
		return nonNull(executeWithNativeSession((HibernateCallback<List<T>>) session -> {
			Criteria criteria = session.createCriteria(entityClass);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			prepareCriteria(criteria);
			return criteria.list();
		}));
	}
