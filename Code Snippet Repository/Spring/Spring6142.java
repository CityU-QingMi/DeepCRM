	private void setDatabaseName(Element element, BeanDefinitionBuilder builder) {
		// 1) Check for an explicit database name
		String name = element.getAttribute(DB_NAME_ATTRIBUTE);

		// 2) Fall back to an implicit database name based on the ID
		if (!StringUtils.hasText(name)) {
			name = element.getAttribute(ID_ATTRIBUTE);
		}

		if (StringUtils.hasText(name)) {
			builder.addPropertyValue("databaseName", name);
		}
		// else, let EmbeddedDatabaseFactory use the default "testdb" name
	}
