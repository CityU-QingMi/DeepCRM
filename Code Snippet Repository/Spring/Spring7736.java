	@Override
	@SuppressWarnings({"", "", ""})
	public List<?> findByNamedParam(final String queryString, final String[] paramNames, final Object[] values)
			throws DataAccessException {

		if (paramNames.length != values.length) {
			throw new IllegalArgumentException("Length of paramNames array must match length of values array");
		}
		return nonNull(executeWithNativeSession((HibernateCallback<List<?>>) session -> {
			org.hibernate.Query queryObject = queryObject(
					ReflectionUtils.invokeMethod(createQueryMethod, session, queryString));
			prepareQuery(queryObject);
			for (int i = 0; i < values.length; i++) {
				applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
			}
			return queryObject.list();
		}));
	}
