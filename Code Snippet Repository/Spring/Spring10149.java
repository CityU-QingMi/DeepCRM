	@Test
	public void prettyPrintingFalse() throws Exception {
		this.factory.setPrettyPrinting(false);
		this.factory.afterPropertiesSet();
		Gson gson = this.factory.getObject();
		StringBean bean = new StringBean();
		bean.setName("Jason");
		String result = gson.toJson(bean);
		assertEquals("{\"name\":\"Jason\"}", result);
	}
