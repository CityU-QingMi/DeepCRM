	@Before
	public void setUp() throws Exception {
		s1 = new SomeObject() {
			@Override
			public String toString() {
				return "A";
			}
		};
		s2 = new SomeObject() {
			@Override
			public String toString() {
				return "B";
			}
		};
		s3 = new SomeObject() {
			@Override
			public String toString() {
				return "C";
			}
		};
	}
