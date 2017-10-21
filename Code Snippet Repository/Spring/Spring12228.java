		@SuppressWarnings("")
		private <T> void sendInternal(T data, @Nullable MediaType mediaType) throws IOException {
			if (logger.isTraceEnabled()) {
				logger.trace("Writing [" + data + "]");
			}
			for (HttpMessageConverter<?> converter : ResponseBodyEmitterReturnValueHandler.this.messageConverters) {
				if (converter.canWrite(data.getClass(), mediaType)) {
					((HttpMessageConverter<T>) converter).write(data, mediaType, this.outputMessage);
					this.outputMessage.flush();
					return;
				}
			}
			throw new IllegalArgumentException("No suitable converter for " + data.getClass());
		}
