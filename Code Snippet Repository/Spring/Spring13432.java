	protected MockPageContext createAndPopulatePageContext() throws JspException {
		MockPageContext pageContext = createPageContext();
		MockHttpServletRequest request = (MockHttpServletRequest) pageContext.getRequest();
		StaticWebApplicationContext wac = (StaticWebApplicationContext) RequestContextUtils.findWebApplicationContext(request);
		wac.registerSingleton("requestDataValueProcessor", RequestDataValueProcessorWrapper.class);
		extendRequest(request);
		extendPageContext(pageContext);
		RequestContext requestContext = new JspAwareRequestContext(pageContext);
		pageContext.setAttribute(RequestContextAwareTag.REQUEST_CONTEXT_PAGE_ATTRIBUTE, requestContext);
		return pageContext;
	}
