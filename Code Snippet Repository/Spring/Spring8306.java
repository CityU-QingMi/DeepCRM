	private void parent(MockHttpServletRequest request, @Nullable RequestBuilder parent) {
		if (parent == null) {
			return;
		}

		MockHttpServletRequest parentRequest = parent.buildRequest(request.getServletContext());

		// session
		HttpSession parentSession = parentRequest.getSession(false);
		if (parentSession != null) {
			HttpSession localSession = request.getSession();
			Assert.state(localSession != null, "No local HttpSession");
			Enumeration<String> attrNames = parentSession.getAttributeNames();
			while (attrNames.hasMoreElements()) {
				String attrName = attrNames.nextElement();
				Object attrValue = parentSession.getAttribute(attrName);
				localSession.setAttribute(attrName, attrValue);
			}
		}

		// header
		Enumeration<String> headerNames = parentRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String attrName = headerNames.nextElement();
			Enumeration<String> attrValues = parentRequest.getHeaders(attrName);
			while (attrValues.hasMoreElements()) {
				String attrValue = attrValues.nextElement();
				request.addHeader(attrName, attrValue);
			}
		}

		// parameter
		Map<String, String[]> parentParams = parentRequest.getParameterMap();
		parentParams.forEach(request::addParameter);

		// cookie
		Cookie[] parentCookies = parentRequest.getCookies();
		if (!ObjectUtils.isEmpty(parentCookies)) {
			request.setCookies(parentCookies);
		}

		// request attribute
		Enumeration<String> parentAttrNames = parentRequest.getAttributeNames();
		while (parentAttrNames.hasMoreElements()) {
			String parentAttrName = parentAttrNames.nextElement();
			request.setAttribute(parentAttrName, parentRequest.getAttribute(parentAttrName));
		}
	}
