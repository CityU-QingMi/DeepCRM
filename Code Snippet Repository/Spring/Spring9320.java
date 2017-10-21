		@Override
		public void accept(FluxSink<Part> emitter) {
			HttpHeaders headers = this.inputMessage.getHeaders();
			MediaType mediaType = headers.getContentType();
			Assert.state(mediaType != null, "No content type set");

			int length = Math.toIntExact(headers.getContentLength());
			Charset charset = Optional.ofNullable(mediaType.getCharset()).orElse(StandardCharsets.UTF_8);
			MultipartContext context = new MultipartContext(mediaType.toString(), length, charset.name());

			NioMultipartParserListener listener = new FluxSinkAdapterListener(emitter, this.bufferFactory, context);
			NioMultipartParser parser = Multipart.multipart(context).forNIO(listener);

			this.inputMessage.getBody().subscribe(buffer -> {
				byte[] resultBytes = new byte[buffer.readableByteCount()];
				buffer.read(resultBytes);
				try {
					parser.write(resultBytes);
				}
				catch (IOException ex) {
					listener.onError("Exception thrown providing input to the parser", ex);
				}
				finally {
					DataBufferUtils.release(buffer);
				}
			}, (ex) -> {
				try {
					listener.onError("Request body input error", ex);
					parser.close();
				}
				catch (IOException ex2) {
					listener.onError("Exception thrown while closing the parser", ex2);
				}
			}, () -> {
				try {
					parser.close();
				}
				catch (IOException ex) {
					listener.onError("Exception thrown while closing the parser", ex);
				}
			});

		}
