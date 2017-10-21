	@Override
	public final void handle(MvcResult result) throws Exception {
		this.printer.printHeading("MockHttpServletRequest");
		printRequest(result.getRequest());

		this.printer.printHeading("Handler");
		printHandler(result.getHandler(), result.getInterceptors());

		this.printer.printHeading("Async");
		printAsyncResult(result);

		this.printer.printHeading("Resolved Exception");
		printResolvedException(result.getResolvedException());

		this.printer.printHeading("ModelAndView");
		printModelAndView(result.getModelAndView());

		this.printer.printHeading("FlashMap");
		printFlashMap(RequestContextUtils.getOutputFlashMap(result.getRequest()));

		this.printer.printHeading("MockHttpServletResponse");
		printResponse(result.getResponse());
	}
