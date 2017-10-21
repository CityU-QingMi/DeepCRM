	protected final boolean isRollback(TestContext testContext) throws Exception {
		boolean rollback = isDefaultRollback(testContext);
		Rollback rollbackAnnotation =
				AnnotatedElementUtils.findMergedAnnotation(testContext.getTestMethod(), Rollback.class);
		if (rollbackAnnotation != null) {
			boolean rollbackOverride = rollbackAnnotation.value();
			if (logger.isDebugEnabled()) {
				logger.debug(String.format(
						"Method-level @Rollback(%s) overrides default rollback [%s] for test context %s.",
						rollbackOverride, rollback, testContext));
			}
			rollback = rollbackOverride;
		}
		else {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format(
						"No method-level @Rollback override: using default rollback [%s] for test context %s.",
						rollback, testContext));
			}
		}
		return rollback;
	}
