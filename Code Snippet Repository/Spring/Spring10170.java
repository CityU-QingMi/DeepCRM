	@Test
	public void setModules() {
		NumberSerializer serializer = new NumberSerializer(Integer.class);
		SimpleModule module = new SimpleModule();
		module.addSerializer(Integer.class, serializer);

		this.factory.setModules(Arrays.asList(new Module[]{module}));
		this.factory.afterPropertiesSet();
		ObjectMapper objectMapper = this.factory.getObject();

		Serializers serializers = getSerializerFactoryConfig(objectMapper).serializers().iterator().next();
		assertSame(serializer, serializers.findSerializer(null, SimpleType.construct(Integer.class), null));
	}
