	protected void addTestSkills() {
		try {
			for (int i = 0, j = TEST_SKILLS.length; i < j; i++) {
				skillDao.merge(TEST_SKILLS[i]);
			}
			if (log.isInfoEnabled()) {
				log.info("TestDataProvider - [addTestSkills]: Added test skill data.");
			}
		} catch (StorageException e) {
			log.error("TestDataProvider - [addTestSkills]: Exception catched: " + e.getMessage());
		}
	}
