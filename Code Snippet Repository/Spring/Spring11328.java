	@Override
	protected Mono<Void> renderInternal(Map<String, Object> renderAttributes,
			@Nullable MediaType contentType, ServerWebExchange exchange) {

		// Expose all standard FreeMarker hash models.
		SimpleHash freeMarkerModel = getTemplateModel(renderAttributes, exchange);
		if (logger.isDebugEnabled()) {
			logger.debug("Rendering FreeMarker template [" + getUrl() + "].");
		}

		Locale locale = LocaleContextHolder.getLocale(exchange.getLocaleContext());
		DataBuffer dataBuffer = exchange.getResponse().bufferFactory().allocateBuffer();
		try {
			Charset charset = getCharset(contentType);
			Writer writer = new OutputStreamWriter(dataBuffer.asOutputStream(), charset);
			getTemplate(locale).process(freeMarkerModel, writer);
		}
		catch (IOException ex) {
			DataBufferUtils.release(dataBuffer);
			String message = "Could not load FreeMarker template for URL [" + getUrl() + "]";
			return Mono.error(new IllegalStateException(message, ex));
		}
		catch (Throwable ex) {
			DataBufferUtils.release(dataBuffer);
			return Mono.error(ex);
		}
		return exchange.getResponse().writeWith(Flux.just(dataBuffer));
	}
