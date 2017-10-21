	protected void addTestEmployees() {
		try {
			for (int i = 0, j = TEST_EMPLOYEES.length; i < j; i++) {
				employeeDao.merge(TEST_EMPLOYEES[i]);
			}
			if (log.isInfoEnabled()) {
				log.info("TestDataProvider - [addTestEmployees]: Added test employee data.");
			}
		} catch (StorageException e) {
			log.error("TestDataProvider - [addTestEmployees]: Exception catched: " + e.getMessage());
		}
	}
