	@Test
	public void setMixIns() {
		Class<?> target = String.class;
		Class<?> mixinSource = Object.class;
		Map<Class<?>, Class<?>> mixIns = new HashMap<>();
		mixIns.put(target, mixinSource);

		this.factory.setModules(Collections.emptyList());
		this.factory.setMixIns(mixIns);
		this.factory.afterPropertiesSet();
		ObjectMapper objectMapper = this.factory.getObject();

		assertEquals(1, objectMapper.mixInCount());
		assertSame(mixinSource, objectMapper.findMixInClassFor(target));
	}
