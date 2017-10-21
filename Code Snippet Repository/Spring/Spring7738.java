	@Override
	@SuppressWarnings({"", "", ""})
	public List<?> findByNamedQuery(final String queryName, @Nullable final Object... values) throws DataAccessException {
		return nonNull(executeWithNativeSession((HibernateCallback<List<?>>) session -> {
			org.hibernate.Query queryObject = queryObject(
					ReflectionUtils.invokeMethod(getNamedQueryMethod, session, queryName));
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.list();
		}));
	}
