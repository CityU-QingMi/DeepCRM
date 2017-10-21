	@Before
	public void setup() {

		SampleController controller = new SampleController("a string value", 3, new Person("a name"));

		this.mockMvc = standaloneSetup(controller)
				.defaultRequest(get("/"))
				.alwaysExpect(status().isOk())
				.setControllerAdvice(new ModelAttributeAdvice())
				.build();
	}
