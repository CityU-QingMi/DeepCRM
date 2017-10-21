	public String sqlCreateString(Dialect dialect, Mapping mapping, String defaultCatalog, String defaultSchema)
			throws HibernateException {
		return buildSqlCreateIndexString(
				dialect,
				getQuotedName( dialect ),
				getTable(),
				getColumnIterator(),
				columnOrderMap,
				false,
				defaultCatalog,
				defaultSchema
		);
	}
