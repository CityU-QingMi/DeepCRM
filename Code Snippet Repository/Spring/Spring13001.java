	@Test
	public void handleReturnTypeResourceIllegalByteRange() throws Exception {
		Resource returnValue = new ByteArrayResource("Content".getBytes(StandardCharsets.UTF_8));
		servletRequest.addHeader("Range", "illegal");

		given(resourceRegionMessageConverter.canWrite(any(), eq(null))).willReturn(true);
		given(resourceRegionMessageConverter.canWrite(any(), eq(MediaType.APPLICATION_OCTET_STREAM))).willReturn(true);

		processor.handleReturnValue(returnValue, returnTypeResource, mavContainer, webRequest);

		then(resourceRegionMessageConverter).should(never()).write(
				anyCollection(), eq(MediaType.APPLICATION_OCTET_STREAM), any(HttpOutputMessage.class));
		assertEquals(416, servletResponse.getStatus());
	}
