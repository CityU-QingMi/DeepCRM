	@Test
	public void mixIn() {
		Class<?> target = String.class;
		Class<?> mixInSource = Object.class;

		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().modules()
				.mixIn(target, mixInSource).build();

		assertEquals(1, objectMapper.mixInCount());
		assertSame(mixInSource, objectMapper.findMixInClassFor(target));
	}
