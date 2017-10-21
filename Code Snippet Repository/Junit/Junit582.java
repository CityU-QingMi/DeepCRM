	@Test
	void closesInputStream() {
		AtomicBoolean closed = new AtomicBoolean(false);
		InputStream inputStream = new ByteArrayInputStream("foo".getBytes()) {

			@Override
			public void close() throws IOException {
				closed.set(true);
			}
		};

		Stream<Object[]> arguments = provideArguments(inputStream, "\n", ',');

		assertThat(arguments.count()).isEqualTo(1);
		assertThat(closed.get()).describedAs("closed").isTrue();
	}
