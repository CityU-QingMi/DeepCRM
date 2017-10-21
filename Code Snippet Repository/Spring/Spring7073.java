	private void assertInboundHeader(javax.jms.Message jmsMessage, String headerId, Object value) {
		Map<String, Object> headers = mapper.toHeaders(jmsMessage);
		Object headerValue = headers.get(headerId);
		if (value == null) {
			assertNull(headerValue);
		}
		else {
			assertNotNull(headerValue);
			assertEquals(value.getClass(), headerValue.getClass());
			assertEquals(value, headerValue);
		}
	}
