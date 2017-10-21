	@BeforeEach
	void init() {
		instanceMap.clear();
		testsInvoked.clear();
		instanceCount = 0;
		nestedInstanceCount = 0;
		beforeAllCount = 0;
		afterAllCount = 0;
		beforeEachCount = 0;
		afterEachCount = 0;
	}
