	protected void checkWillTranslateExceptions(Object o) {
		assertTrue(o instanceof Advised);
		Advised a = (Advised) o;
		for (Advisor advisor : a.getAdvisors()) {
			if (advisor instanceof PersistenceExceptionTranslationAdvisor) {
				return;
			}
		}
		fail("No translation");
	}
