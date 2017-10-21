		@RequestMapping("")
		public void handle(@PathVariable("hotel") String hotel,
				@PathVariable int booking,
				@PathVariable String other,
				@MatrixVariable(name = "q", pathVar = "hotel") int qHotel,
				@MatrixVariable(name = "q", pathVar = "other") int qOther,
				Writer writer) throws IOException {
			assertEquals("Invalid path variable value", "42", hotel);
			assertEquals("Invalid path variable value", 21, booking);
			writer.write("test-" + hotel + "-q" + qHotel + "-" + booking + "-" + other + "-q" + qOther);
		}
