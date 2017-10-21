	@Override
	@SuppressWarnings({"", "", ""})
	public List<?> findByNamedQueryAndNamedParam(
			final String queryName, @Nullable final String[] paramNames, @Nullable final Object[] values)
			throws DataAccessException {

		if (values != null && (paramNames == null || paramNames.length != values.length)) {
			throw new IllegalArgumentException("Length of paramNames array must match length of values array");
		}
		return nonNull(executeWithNativeSession((HibernateCallback<List<?>>) session -> {
			org.hibernate.Query queryObject = (org.hibernate.Query)
					nonNull(ReflectionUtils.invokeMethod(getNamedQueryMethod, session, queryName));
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
				}
			}
			return queryObject.list();
		}));
	}
