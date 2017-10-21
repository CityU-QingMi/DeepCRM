	@Test
	public void renderWithJsonp() throws Exception {
		testJsonp("jsonp", "callback", true);
		testJsonp("jsonp", "_callback", true);
		testJsonp("jsonp", "_Call.bAcK", true);
		testJsonp("jsonp", "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.", true);

		testJsonp("jsonp", "<script>", false);
		testJsonp("jsonp", "!foo!bar", false);
	}
