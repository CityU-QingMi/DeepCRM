	@Test
	public void createDefaultConversionServiceWithSupplements() {
		ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
		Set<Object> converters = new HashSet<>();
		converters.add(new Converter<String, Foo>() {
			@Override
			public Foo convert(String source) {
				return new Foo();
			}
		});
		converters.add(new ConverterFactory<String, Bar>() {
			@Override
			public <T extends Bar> Converter<String, T> getConverter(Class<T> targetType) {
				return new Converter<String, T> () {
					@SuppressWarnings("unchecked")
					@Override
					public T convert(String source) {
						return (T) new Bar();
					}
				};
			}
		});
		converters.add(new GenericConverter() {
			@Override
			public Set<ConvertiblePair> getConvertibleTypes() {
				return Collections.singleton(new ConvertiblePair(String.class, Baz.class));
			}
			@Override
			@Nullable
			public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
				return new Baz();
			}
		});
		factory.setConverters(converters);
		factory.afterPropertiesSet();
		ConversionService service = factory.getObject();
		assertTrue(service.canConvert(String.class, Integer.class));
		assertTrue(service.canConvert(String.class, Foo.class));
		assertTrue(service.canConvert(String.class, Bar.class));
		assertTrue(service.canConvert(String.class, Baz.class));
	}
