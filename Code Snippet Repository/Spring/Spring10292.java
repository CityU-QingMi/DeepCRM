		@Override
		public Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response) {
			try {
				ZeroCopyHttpOutputMessage zeroCopyResponse =
						(ZeroCopyHttpOutputMessage) response;

				Resource logo = new ClassPathResource("spring.png",
						ZeroCopyIntegrationTests.class);
				File logoFile = logo.getFile();
				zeroCopyResponse.getHeaders().setContentType(MediaType.IMAGE_PNG);
				zeroCopyResponse.getHeaders().setContentLength(logoFile.length());
				return zeroCopyResponse.writeWith(logoFile, 0, logoFile.length());

			}
			catch (Throwable ex) {
				return Mono.error(ex);
			}


		}
