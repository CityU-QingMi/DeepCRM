	@Test
	public void setTimeZone() {
		TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");

		this.factory.setTimeZone(timeZone);
		this.factory.afterPropertiesSet();

		assertEquals(timeZone, this.factory.getObject().getSerializationConfig().getTimeZone());
		assertEquals(timeZone, this.factory.getObject().getDeserializationConfig().getTimeZone());
	}
