	@Override
	@SuppressWarnings({"", ""})
	public Iterator<?> iterate(final String queryString, @Nullable final Object... values) throws DataAccessException {
		return nonNull(executeWithNativeSession((HibernateCallback<Iterator<?>>) session -> {
			org.hibernate.Query queryObject = queryObject(
					ReflectionUtils.invokeMethod(createQueryMethod, session, queryString));
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.iterate();
		}));
	}
