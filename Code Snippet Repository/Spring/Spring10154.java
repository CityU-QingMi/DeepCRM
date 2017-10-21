	@Test
	public void base64EncodeByteArrays() throws Exception {
		this.factory.setBase64EncodeByteArrays(true);
		this.factory.afterPropertiesSet();
		Gson gson = this.factory.getObject();
		ByteArrayBean bean = new ByteArrayBean();
		bean.setBytes(new byte[] {0x1, 0x2});
		String result = gson.toJson(bean);
		assertEquals("{\"bytes\":\"AQI\\u003d\"}", result);
	}
