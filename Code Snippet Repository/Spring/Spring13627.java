	@Override
	protected TestBean createTestBean() {
		// set up test data
		this.rob = new TestBean();
		rob.setName("Rob");
		rob.setMyFloat(new Float(12.34));

		TestBean sally = new TestBean();
		sally.setName("Sally");
		rob.setSpouse(sally);

		return rob;
	}
