	@Override
	@SuppressWarnings({"", "", ""})
	public List<?> findByValueBean(final String queryString, final Object valueBean)
			throws DataAccessException {

		return nonNull(executeWithNativeSession((HibernateCallback<List<?>>) session -> {
			org.hibernate.Query queryObject = queryObject(
					ReflectionUtils.invokeMethod(createQueryMethod, session, queryString));
			prepareQuery(queryObject);
			queryObject.setProperties(valueBean);
			return queryObject.list();
		}));
	}
