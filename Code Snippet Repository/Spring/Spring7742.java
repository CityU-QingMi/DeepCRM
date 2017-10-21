	@Override
	@SuppressWarnings("")
	public List<?> findByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults)
			throws DataAccessException {

		Assert.notNull(criteria, "DetachedCriteria must not be null");
		return nonNull(executeWithNativeSession((HibernateCallback<List<?>>) session -> {
			Criteria executableCriteria = criteria.getExecutableCriteria(session);
			prepareCriteria(executableCriteria);
			if (firstResult >= 0) {
				executableCriteria.setFirstResult(firstResult);
			}
			if (maxResults > 0) {
				executableCriteria.setMaxResults(maxResults);
			}
			return executableCriteria.list();
		}));
	}
