	@SuppressWarnings({ "", "" })
	@Test
	void toStreamWithCollection() {
		AtomicBoolean collectionStreamClosed = new AtomicBoolean(false);
		Collection<String> input = new ArrayList<String>() {

			{
				add("foo");
				add("bar");
			}

			@Override
			public Stream<String> stream() {
				return super.stream().onClose(() -> collectionStreamClosed.set(true));
			}
		};

		try (Stream<String> stream = (Stream<String>) CollectionUtils.toStream(input)) {
			List<String> result = stream.collect(toList());
			assertThat(result).containsExactly("foo", "bar");
		}

		assertThat(collectionStreamClosed.get()).describedAs("collectionStreamClosed").isTrue();
	}
