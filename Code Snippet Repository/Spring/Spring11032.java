	@Test
	public void match() throws Exception {
		Map<String, String> expected = new HashMap<>(2);
		expected.put("booking", "42");
		expected.put("hotel", "1");

		UriTemplate template = new UriTemplate("/hotels/{hotel}/bookings/{booking}");
		Map<String, String> result = template.match("/hotels/1/bookings/42");
		assertEquals("Invalid match", expected, result);
	}
