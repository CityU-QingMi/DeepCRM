	@SuppressWarnings("")
	@Test
	void toStreamWithIterable() {

		Iterable<String> input = new Iterable<String>() {

			@Override
			public Iterator<String> iterator() {
				return asList("foo", "bar").iterator();
			}
		};

		Stream<String> result = (Stream<String>) CollectionUtils.toStream(input);

		assertThat(result).containsExactly("foo", "bar");
	}
