	@Override
	@SuppressWarnings({"", ""})
	public <T> List<T> findByExample(
			@Nullable final String entityName, final T exampleEntity, final int firstResult, final int maxResults)
			throws DataAccessException {

		Assert.notNull(exampleEntity, "Example entity must not be null");
		return nonNull(executeWithNativeSession((HibernateCallback<List<T>>) session -> {
			Criteria executableCriteria = (entityName != null ?
					session.createCriteria(entityName) : session.createCriteria(exampleEntity.getClass()));
			executableCriteria.add(Example.create(exampleEntity));
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
