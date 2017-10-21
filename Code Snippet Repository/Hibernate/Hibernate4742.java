	private ImportSqlCommandExtractor instantiateExplicitCommandExtractor(
			String extractorClassName,
			ClassLoaderService classLoaderService) {
		try {
			return (ImportSqlCommandExtractor) classLoaderService.classForName( extractorClassName ).newInstance();
		}
		catch (Exception e) {
			throw new HibernateException(
					"Could not instantiate import sql command extractor [" + extractorClassName + "]", e
			);
		}
	}
