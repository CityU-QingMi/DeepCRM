	@Test
	public void canWrite() throws Exception {
		Marshaller marshaller = mock(Marshaller.class);

		given(marshaller.supports(Integer.class)).willReturn(false);
		given(marshaller.supports(String.class)).willReturn(true);

		MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
		converter.setMarshaller(marshaller);

		assertFalse(converter.canWrite(Boolean.class, MediaType.TEXT_PLAIN));
		assertFalse(converter.canWrite(Integer.class, MediaType.TEXT_XML));
		assertTrue(converter.canWrite(String.class, MediaType.TEXT_XML));
	}
