	public MapSqlParameterSource addValues(@Nullable Map<String, ?> values) {
		if (values != null) {
			values.forEach((key, value) -> {
				this.values.put(key, value);
				if (value instanceof SqlParameterValue) {
					registerSqlType(key, ((SqlParameterValue) value).getSqlType());
				}
			});
		}
		return this;
	}
