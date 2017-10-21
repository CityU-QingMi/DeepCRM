	private void executeSqlScripts(TestContext testContext, ExecutionPhase executionPhase) throws Exception {
		boolean classLevel = false;

		Set<Sql> sqlAnnotations = AnnotatedElementUtils.getMergedRepeatableAnnotations(
				testContext.getTestMethod(), Sql.class, SqlGroup.class);
		if (sqlAnnotations.isEmpty()) {
			sqlAnnotations = AnnotatedElementUtils.getMergedRepeatableAnnotations(
					testContext.getTestClass(), Sql.class, SqlGroup.class);
			if (!sqlAnnotations.isEmpty()) {
				classLevel = true;
			}
		}

		for (Sql sql : sqlAnnotations) {
			executeSqlScripts(sql, executionPhase, testContext, classLevel);
		}
	}
