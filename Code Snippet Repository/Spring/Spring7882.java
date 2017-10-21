	@Before
	public void setUp() {
		if (applicationContext == null) {
			applicationContext = new ClassPathXmlApplicationContext(getConfigLocations());
		}
		applicationContext.getAutowireCapableBeanFactory().autowireBean(this);

		if (this.transactionManager != null && this.transactionDefinition != null) {
			startNewTransaction();
		}
	}
