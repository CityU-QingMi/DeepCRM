	@Test
	public void disableHtmlEscapingFalse() throws Exception {
		this.factory.setDisableHtmlEscaping(false);
		this.factory.afterPropertiesSet();
		Gson gson = this.factory.getObject();
		StringBean bean = new StringBean();
		bean.setName("Bob=Bob");
		String result = gson.toJson(bean);
		assertEquals("{\"name\":\"Bob\\u003dBob\"}", result);
	}
