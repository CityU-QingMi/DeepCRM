	@Override
	@SuppressWarnings({"", "", ""})
	public List<?> findByNamedQueryAndValueBean(final String queryName, final Object valueBean)
			throws DataAccessException {

		return nonNull(executeWithNativeSession((HibernateCallback<List<?>>) session -> {
			org.hibernate.Query queryObject = queryObject(
					ReflectionUtils.invokeMethod(getNamedQueryMethod, session, queryName));
			prepareQuery(queryObject);
			queryObject.setProperties(valueBean);
			return queryObject.list();
		}));
	}
