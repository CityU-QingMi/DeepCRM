	@Test
	public void testSerializability() throws IOException, ClassNotFoundException {
		HttpStatusCodeException ex1 = new HttpClientErrorException(
				HttpStatus.BAD_REQUEST, null, null, StandardCharsets.US_ASCII);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		new ObjectOutputStream(out).writeObject(ex1);
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		HttpStatusCodeException ex2 =
				(HttpStatusCodeException) new ObjectInputStream(in).readObject();
		assertThat(ex2.getResponseBodyAsString(), equalTo(ex1.getResponseBodyAsString()));
	}
