	@Test
	public void postMono() {
		URI uri = URI.create("http://localhost:" + port + "/mono");
		Person person = new Person("Jack");
		RequestEntity<Person> requestEntity = RequestEntity.post(uri).body(person);
		ResponseEntity<Person> result = restTemplate.exchange(requestEntity, Person.class);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals("Jack", result.getBody().getName());
	}
