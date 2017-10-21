	@Override
	public String toString() {
		return String.format(
				"%s[converterClass=%s, domainType=%s, jdbcType=%s]",
				this.getClass().getName(),
				attributeConverter.getClass().getName(),
				entityAttributeType.getName(),
				databaseColumnType.getName()
		);
	}
