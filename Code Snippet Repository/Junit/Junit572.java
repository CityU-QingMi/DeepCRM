	@Override
	public void accept(ValueSource source) {
		List<Object> arrays = Stream.of(source.strings(), source.ints(), source.longs(), source.doubles()) //
				.filter(array -> Array.getLength(array) > 0) //
				.collect(toList());
		Preconditions.condition(arrays.size() == 1, () -> "Exactly one type of input must be provided in the @"
				+ ValueSource.class.getSimpleName() + " annotation but there were " + arrays.size());
		Object originalArray = arrays.get(0);
		arguments = IntStream.range(0, Array.getLength(originalArray)) //
				.mapToObj(index -> Array.get(originalArray, index)) //
				.toArray();
	}
