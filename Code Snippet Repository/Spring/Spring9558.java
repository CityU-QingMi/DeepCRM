	@Override
	@SuppressWarnings({"", "", ""})
	public T extractData(ClientHttpResponse response) throws IOException {
		MessageBodyClientHttpResponseWrapper responseWrapper = new MessageBodyClientHttpResponseWrapper(response);
		if (!responseWrapper.hasMessageBody() || responseWrapper.hasEmptyMessageBody()) {
			return null;
		}
		MediaType contentType = getContentType(responseWrapper);

		try {
			for (HttpMessageConverter<?> messageConverter : this.messageConverters) {
				if (messageConverter instanceof GenericHttpMessageConverter) {
					GenericHttpMessageConverter<?> genericMessageConverter =
							(GenericHttpMessageConverter<?>) messageConverter;
					if (genericMessageConverter.canRead(this.responseType, null, contentType)) {
						if (logger.isDebugEnabled()) {
							logger.debug("Reading [" + this.responseType + "] as \"" +
									contentType + "\" using [" + messageConverter + "]");
						}
						return (T) genericMessageConverter.read(this.responseType, null, responseWrapper);
					}
				}
				if (this.responseClass != null) {
					if (messageConverter.canRead(this.responseClass, contentType)) {
						if (logger.isDebugEnabled()) {
							logger.debug("Reading [" + this.responseClass.getName() + "] as \"" +
									contentType + "\" using [" + messageConverter + "]");
						}
						return (T) messageConverter.read((Class) this.responseClass, responseWrapper);
					}
				}
			}
		}
		catch (IOException | HttpMessageNotReadableException ex) {
			throw new RestClientException("Error while extracting response for type [" +
					this.responseType + "] and content type [" + contentType + "]", ex);
		}

		throw new RestClientException("Could not extract response: no suitable HttpMessageConverter found " +
				"for response type [" + this.responseType + "] and content type [" + contentType + "]");
	}
