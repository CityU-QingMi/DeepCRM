	@Test
	public void testByteArrayConversion() throws JMSException {
		Session session = mock(Session.class);
		BytesMessage message = mock(BytesMessage.class);

		byte[] content = "test".getBytes();
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content);

		given(session.createBytesMessage()).willReturn(message);
		given(message.getBodyLength()).willReturn((long) content.length);
		given(message.readBytes(any(byte[].class))).willAnswer(new Answer<Integer>() {
			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				return byteArrayInputStream.read((byte[]) invocation.getArguments()[0]);
			}
		});

		SimpleMessageConverter converter = new SimpleMessageConverter();
		Message msg = converter.toMessage(content, session);
		assertEquals(content.length, ((byte[]) converter.fromMessage(msg)).length);

		verify(message).writeBytes(content);
	}
