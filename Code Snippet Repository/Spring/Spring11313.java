	protected final String createTargetUrl(Map<String, Object> model, ServerWebExchange exchange) {
		String url = getUrl();
		Assert.state(url != null, "'url' not set");

		ServerHttpRequest request = exchange.getRequest();

		StringBuilder targetUrl = new StringBuilder();
		if (isContextRelative() && url.startsWith("/")) {
			targetUrl.append(request.getPath().contextPath().value());
		}
		targetUrl.append(url);

		if (StringUtils.hasText(targetUrl)) {
			Map<String, String> uriVars = getCurrentUriVariables(exchange);
			targetUrl = expandTargetUrlTemplate(targetUrl.toString(), model, uriVars);
		}

		if (isPropagateQuery()) {
			targetUrl = appendCurrentRequestQuery(targetUrl.toString(), request);
		}

		String result = targetUrl.toString();

		RequestDataValueProcessor processor = getRequestDataValueProcessor();
		return (processor != null ? processor.processUrl(exchange, result) : result);
	}
