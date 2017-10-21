	@Override
	protected TestBean createTestBean() {
		// set up test data
		this.rob = new TestBean();
		this.rob.setName("Rob");
		this.rob.setMyFloat(new Float(12.34));

		TestBean sally = new TestBean();
		sally.setName("Sally");
		this.rob.setSpouse(sally);

		return this.rob;
	}
