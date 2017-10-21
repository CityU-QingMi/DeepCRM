	public static Stream<?> toStream(Object object) {
		Preconditions.notNull(object, "Object must not be null");
		if (object instanceof Stream) {
			return (Stream<?>) object;
		}
		if (object instanceof DoubleStream) {
			return ((DoubleStream) object).boxed();
		}
		if (object instanceof IntStream) {
			return ((IntStream) object).boxed();
		}
		if (object instanceof LongStream) {
			return ((LongStream) object).boxed();
		}
		if (object instanceof Collection) {
			return ((Collection<?>) object).stream();
		}
		if (object instanceof Iterable) {
			return stream(((Iterable<?>) object).spliterator(), false);
		}
		if (object instanceof Iterator) {
			return stream(spliteratorUnknownSize((Iterator<?>) object, ORDERED), false);
		}
		if (object instanceof Object[]) {
			return Arrays.stream((Object[]) object);
		}
		if (object instanceof double[]) {
			return DoubleStream.of((double[]) object).boxed();
		}
		if (object instanceof int[]) {
			return IntStream.of((int[]) object).boxed();
		}
		if (object instanceof long[]) {
			return LongStream.of((long[]) object).boxed();
		}
		if (object.getClass().isArray() && object.getClass().getComponentType().isPrimitive()) {
			return IntStream.range(0, Array.getLength(object)).mapToObj(i -> Array.get(object, i));
		}
		throw new PreconditionViolationException(
			"Cannot convert instance of " + object.getClass().getName() + " into a Stream: " + object);
	}
