	@Test
	public void testWithMessageContentsDelegateForBytesMessage() throws Exception {
		BytesMessage bytesMessage = mock(BytesMessage.class);
		// BytesMessage contents must be unwrapped...
		given(bytesMessage.getBodyLength()).willReturn(new Long(TEXT.getBytes().length));
		given(bytesMessage.readBytes(any(byte[].class))).willAnswer(new Answer<Integer>() {
			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				byte[] bytes = (byte[]) invocation.getArguments()[0];
				ByteArrayInputStream inputStream = new ByteArrayInputStream(TEXT.getBytes());
				return inputStream.read(bytes);
			}
		});

		MessageContentsDelegate delegate = mock(MessageContentsDelegate.class);

		MessageListenerAdapter adapter = new MessageListenerAdapter(delegate);
		adapter.onMessage(bytesMessage);

		verify(delegate).handleMessage(TEXT.getBytes());
	}
