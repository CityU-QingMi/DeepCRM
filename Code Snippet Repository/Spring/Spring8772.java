	@Before
	public void setup() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		this.now = dateFormat.format(new Date(this.currentTime));
		this.minuteAgo = dateFormat.format(new Date(this.currentTime - (1000 * 60)));
		this.secondLater = dateFormat.format(new Date(this.currentTime + 1000));

		PersonController controller = new PersonController();
		controller.setStubTimestamp(this.currentTime);
		this.mockMvc = standaloneSetup(controller).build();
	}
