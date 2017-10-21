	@Test
	public void mediaTypes() throws Exception {

		// Media type from request
		this.servletRequest.addHeader("Accept", "text/event-stream");
		testSseResponse(true);

		// Media type from "produces" attribute
		Set<MediaType> types = Collections.singleton(MediaType.TEXT_EVENT_STREAM);
		this.servletRequest.setAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, types);
		testSseResponse(true);

		// No media type preferences
		testSseResponse(false);
	}
