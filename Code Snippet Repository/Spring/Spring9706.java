	private void updateResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ContentCachingResponseWrapper responseWrapper =
				WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
		Assert.notNull(responseWrapper, "ContentCachingResponseWrapper not found");
		HttpServletResponse rawResponse = (HttpServletResponse) responseWrapper.getResponse();
		int statusCode = responseWrapper.getStatusCode();

		if (rawResponse.isCommitted()) {
			responseWrapper.copyBodyToResponse();
		}
		else if (isEligibleForEtag(request, responseWrapper, statusCode, responseWrapper.getContentInputStream())) {
			String responseETag = generateETagHeaderValue(responseWrapper.getContentInputStream(), this.writeWeakETag);
			rawResponse.setHeader(HEADER_ETAG, responseETag);
			String requestETag = request.getHeader(HEADER_IF_NONE_MATCH);
			if (requestETag != null
					&& (responseETag.equals(requestETag)
					|| responseETag.replaceFirst("^W/", "").equals(requestETag.replaceFirst("^W/", ""))
					|| "*".equals(requestETag))) {
				if (logger.isTraceEnabled()) {
					logger.trace("ETag [" + responseETag + "] equal to If-None-Match, sending 304");
				}
				rawResponse.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			}
			else {
				if (logger.isTraceEnabled()) {
					logger.trace("ETag [" + responseETag + "] not equal to If-None-Match [" + requestETag +
							"], sending normal response");
				}
				responseWrapper.copyBodyToResponse();
			}
		}
		else {
			if (logger.isTraceEnabled()) {
				logger.trace("Response with status code [" + statusCode + "] not eligible for ETag");
			}
			responseWrapper.copyBodyToResponse();
		}
	}
