	@Test
	public void canRead() throws Exception {
		Unmarshaller unmarshaller = mock(Unmarshaller.class);

		given(unmarshaller.supports(Integer.class)).willReturn(false);
		given(unmarshaller.supports(String.class)).willReturn(true);

		MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
		converter.setUnmarshaller(unmarshaller);

		assertFalse(converter.canRead(Boolean.class, MediaType.TEXT_PLAIN));
		assertFalse(converter.canRead(Integer.class, MediaType.TEXT_XML));
		assertTrue(converter.canRead(String.class, MediaType.TEXT_XML));
	}
