	@Test
	void autowiredFields() {
		assertNotNull(this.dilbert, "Dilbert should have been @Autowired by Spring");
		assertEquals("Dilbert", this.dilbert.getName(), "Person's name");
		assertEquals(2, this.people.size(), "Number of people in context");

		assertNotNull(this.dog, "Dogbert should have been @Autowired by Spring");
		assertEquals("Dogbert", this.dog.getName(), "Dog's name");

		assertNotNull(this.cat, "Catbert should have been @Autowired by Spring as the @Primary cat");
		assertEquals("Catbert", this.cat.getName(), "Primary cat's name");
		assertEquals(2, this.cats.size(), "Number of cats in context");

		assertNotNull(this.enigma, "Enigma should have been injected via @Value by Spring");
		assertEquals(new Integer(42), this.enigma, "enigma");
	}
