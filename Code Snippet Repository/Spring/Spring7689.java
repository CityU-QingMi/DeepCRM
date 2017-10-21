	@Test
	public void testToString() {
		ErrorMessage em = new ErrorMessage(new RuntimeException("foo"));
		String emString = em.toString();
		assertThat(emString, not(containsString("original")));

		em = new ErrorMessage(new RuntimeException("foo"), new GenericMessage<>("bar"));
		emString = em.toString();
		assertThat(emString, containsString("original"));
		assertThat(emString, containsString(em.getOriginalMessage().toString()));
	}
