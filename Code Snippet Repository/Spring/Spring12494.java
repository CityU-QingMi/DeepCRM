	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Assert.state(this.renderer != null, "No Renderer set");

		exposeModelAsRequestAttributes(model, request);
		if (this.exposeJstlAttributes) {
			JstlUtils.exposeLocalizationContext(new RequestContext(request, getServletContext()));
		}
		if (this.alwaysInclude) {
			request.setAttribute(AbstractRequest.FORCE_INCLUDE_ATTRIBUTE_NAME, true);
		}

		Request tilesRequest = createTilesRequest(request, response);
		this.renderer.render(getUrl(), tilesRequest);
	}
