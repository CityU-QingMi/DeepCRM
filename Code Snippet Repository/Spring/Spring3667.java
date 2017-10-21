	@Before
	public final void setUp() throws Exception {
		this.server = MBeanServerFactory.createMBeanServer();
		try {
			onSetUp();
		}
		catch (Exception ex) {
			releaseServer();
			throw ex;
		}
	}
