	@Test(expected = ConstraintViolationException.class)
	public void updateSamWithNullDriversLicenseWithSessionFlush() throws Throwable {
		updateSamWithNullDriversLicense();
		// Manual flush is required to avoid false positive in test
		try {
			sessionFactory.getCurrentSession().flush();
		}
		catch (PersistenceException ex) {
			// Wrapped in Hibernate 5.2, with the constraint violation as cause
			throw ex.getCause();
		}
	}
