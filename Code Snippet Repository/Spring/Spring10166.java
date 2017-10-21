	@Test
	public void mixIns() {
		Class<?> target = String.class;
		Class<?> mixInSource = Object.class;
		Map<Class<?>, Class<?>> mixIns = new HashMap<>();
		mixIns.put(target, mixInSource);

		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().modules()
				.mixIns(mixIns).build();

		assertEquals(1, objectMapper.mixInCount());
		assertSame(mixInSource, objectMapper.findMixInClassFor(target));
	}
