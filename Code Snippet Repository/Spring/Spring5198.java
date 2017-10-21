	@Test
	@SuppressWarnings("")
	public void convertObjectToOptional() {
		Method method = ClassUtils.getMethod(TestEntity.class, "handleOptionalValue", Optional.class);
		MethodParameter parameter = new MethodParameter(method, 0);
		TypeDescriptor descriptor = new TypeDescriptor(parameter);
		Object actual = conversionService.convert("1,2,3", TypeDescriptor.valueOf(String.class), descriptor);
		assertEquals(Optional.class, actual.getClass());
		assertEquals(Arrays.asList(1, 2, 3), ((Optional<List<Integer>>) actual).get());
	}
