	@Test
	public void entityList() throws Exception {

		List<Person> expected = Arrays.asList(
				new Person("Jane"), new Person("Jason"), new Person("John"));

		this.client.get()
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBodyList(Person.class).isEqualTo(expected);
	}
