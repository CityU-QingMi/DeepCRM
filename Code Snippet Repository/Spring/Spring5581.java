	protected final ContentHandler mockContentHandler() throws Exception {
		ContentHandler contentHandler = mock(ContentHandler.class);
		willAnswer(new CopyCharsAnswer()).given(contentHandler).characters(any(char[].class), anyInt(), anyInt());
		willAnswer(new CopyCharsAnswer()).given(contentHandler).ignorableWhitespace(any(char[].class), anyInt(), anyInt());
		willAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				invocation.getArguments()[3] = new AttributesImpl((Attributes) invocation.getArguments()[3]);
				return null;
			}
		}).given(contentHandler).startElement(anyString(), anyString(), anyString(), any(Attributes.class));
		return contentHandler;
	}
