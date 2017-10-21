		@Override
		public Object extractData(ClientHttpResponse response) throws IOException {
			HttpStatus httpStatus = HttpStatus.resolve(response.getRawStatusCode());
			if (httpStatus == null) {
				throw new UnknownHttpStatusCodeException(
						response.getRawStatusCode(), response.getStatusText(), response.getHeaders(), null, null);
			}
			if (httpStatus != HttpStatus.OK) {
				throw new HttpServerErrorException(
						httpStatus, response.getStatusText(), response.getHeaders(), null, null);
			}

			if (logger.isTraceEnabled()) {
				logger.trace("XHR receive headers: " + response.getHeaders());
			}
			InputStream is = response.getBody();
			ByteArrayOutputStream os = new ByteArrayOutputStream();

			while (true) {
				if (this.sockJsSession.isDisconnected()) {
					if (logger.isDebugEnabled()) {
						logger.debug("SockJS sockJsSession closed, closing response.");
					}
					response.close();
					break;
				}
				int b = is.read();
				if (b == -1) {
					if (os.size() > 0) {
						handleFrame(os);
					}
					if (logger.isTraceEnabled()) {
						logger.trace("XHR receive completed");
					}
					break;
				}
				if (b == '\n') {
					handleFrame(os);
				}
				else {
					os.write(b);
				}
			}
			return null;
		}
