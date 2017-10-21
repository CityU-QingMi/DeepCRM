	@Test
	public void prettyPrinting() throws Exception {
		this.factory.setPrettyPrinting(true);
		this.factory.afterPropertiesSet();
		Gson gson = this.factory.getObject();
		StringBean bean = new StringBean();
		bean.setName("Jason");
		String result = gson.toJson(bean);
		assertTrue(result.contains("  \"name\": \"Jason\""));
	}
