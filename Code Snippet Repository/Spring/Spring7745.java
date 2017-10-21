	@Override
	@SuppressWarnings({"", ""})
	public int bulkUpdate(final String queryString, @Nullable final Object... values) throws DataAccessException {
		Integer result = executeWithNativeSession(session -> {
			org.hibernate.Query queryObject = queryObject(
					ReflectionUtils.invokeMethod(createQueryMethod, session, queryString));
			prepareQuery(queryObject);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.executeUpdate();
		});
		Assert.state(result != null, "No update count");
		return result;
	}
