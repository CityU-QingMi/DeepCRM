	@Test
	public void base64EncodeByteArraysDisableHtmlEscaping() throws Exception {
		this.factory.setBase64EncodeByteArrays(true);
		this.factory.setDisableHtmlEscaping(true);
		this.factory.afterPropertiesSet();
		Gson gson = this.factory.getObject();
		ByteArrayBean bean = new ByteArrayBean();
		bean.setBytes(new byte[] {0x1, 0x2});
		String result = gson.toJson(bean);
		assertEquals("{\"bytes\":\"AQI=\"}", result);
	}
