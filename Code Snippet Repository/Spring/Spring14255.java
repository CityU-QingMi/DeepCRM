	@Test
	public void frameFormats() throws Exception {
		this.servletRequest.setQueryString("c=callback");
		this.servletRequest.addParameter("c", "callback");

		SockJsFrame frame = SockJsFrame.openFrame();

		SockJsFrameFormat format = new XhrPollingTransportHandler().getFrameFormat(this.request);
		String formatted = format.format(frame);
		assertEquals(frame.getContent() + "\n", formatted);

		format = new XhrStreamingTransportHandler().getFrameFormat(this.request);
		formatted = format.format(frame);
		assertEquals(frame.getContent() + "\n", formatted);

		format = new HtmlFileTransportHandler().getFrameFormat(this.request);
		formatted = format.format(frame);
		assertEquals("<script>\np(\"" + frame.getContent() + "\");\n</script>\r\n", formatted);

		format = new EventSourceTransportHandler().getFrameFormat(this.request);
		formatted = format.format(frame);
		assertEquals("data: " + frame.getContent() + "\r\n\r\n", formatted);

		format = new JsonpPollingTransportHandler().getFrameFormat(this.request);
		formatted = format.format(frame);
		assertEquals("/**/callback(\"" + frame.getContent() + "\");\r\n", formatted);
	}
