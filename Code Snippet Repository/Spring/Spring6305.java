	public void registerTranslator(String dbName, SQLExceptionTranslator translator) {
		SQLExceptionTranslator replaced = translatorMap.put(dbName, translator);
		if (replaced != null) {
			logger.warn("Replacing custom translator [" + replaced + "] for database '" + dbName +
					"' with [" + translator + "]");
		}
		else {
			logger.info("Adding custom translator of type [" + translator.getClass().getName() +
					"] for database '" + dbName + "'");
		}
	}
