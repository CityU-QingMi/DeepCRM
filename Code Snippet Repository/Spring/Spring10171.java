	@Test
	@SuppressWarnings("")
	public void customizeDefaultModulesWithModuleClass() throws JsonProcessingException, UnsupportedEncodingException {
		this.factory.setModulesToInstall(CustomIntegerModule.class);
		this.factory.afterPropertiesSet();
		ObjectMapper objectMapper = this.factory.getObject();

		DateTime dateTime = new DateTime(1322903730000L, DateTimeZone.UTC);
		assertEquals("1322903730000", new String(objectMapper.writeValueAsBytes(dateTime), "UTF-8"));
		assertThat(new String(objectMapper.writeValueAsBytes(new Integer(4)), "UTF-8"), containsString("customid"));
	}
