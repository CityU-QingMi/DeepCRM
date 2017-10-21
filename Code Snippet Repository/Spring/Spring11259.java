	@Override
	protected HandlerMethod handleNoMatch(Set<RequestMappingInfo> infos,
			ServerWebExchange exchange) throws Exception {

		PartialMatchHelper helper = new PartialMatchHelper(infos, exchange);

		if (helper.isEmpty()) {
			return null;
		}

		ServerHttpRequest request = exchange.getRequest();

		if (helper.hasMethodsMismatch()) {
			String httpMethod = request.getMethodValue();
			Set<HttpMethod> methods = helper.getAllowedMethods();
			if (HttpMethod.OPTIONS.matches(httpMethod)) {
				HttpOptionsHandler handler = new HttpOptionsHandler(methods);
				return new HandlerMethod(handler, HTTP_OPTIONS_HANDLE_METHOD);
			}
			throw new MethodNotAllowedException(httpMethod, methods);
		}

		if (helper.hasConsumesMismatch()) {
			Set<MediaType> mediaTypes = helper.getConsumableMediaTypes();
			MediaType contentType;
			try {
				contentType = request.getHeaders().getContentType();
			}
			catch (InvalidMediaTypeException ex) {
				throw new UnsupportedMediaTypeStatusException(ex.getMessage());
			}
			throw new UnsupportedMediaTypeStatusException(contentType, new ArrayList<>(mediaTypes));
		}

		if (helper.hasProducesMismatch()) {
			Set<MediaType> mediaTypes = helper.getProducibleMediaTypes();
			throw new NotAcceptableStatusException(new ArrayList<>(mediaTypes));
		}

		if (helper.hasParamsMismatch()) {
			throw new ServerWebInputException(
					"Unsatisfied query parameter conditions: " + helper.getParamConditions() +
							", actual parameters: " + request.getQueryParams());
		}

		return null;
	}
