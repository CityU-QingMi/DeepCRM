		@Override
		public void handle(ServerHttpRequest request, ServerHttpResponse response) throws IOException {
			if (HttpMethod.GET == request.getMethod()) {
				addNoCacheHeaders(response);
				if (checkOrigin(request, response)) {
					response.getHeaders().setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
					String content = String.format(
							INFO_CONTENT, random.nextInt(), isSessionCookieNeeded(), isWebSocketEnabled());
					response.getBody().write(content.getBytes());
				}

			}
			else if (HttpMethod.OPTIONS == request.getMethod()) {
				if (checkOrigin(request, response)) {
					addCacheHeaders(response);
					response.setStatusCode(HttpStatus.NO_CONTENT);
				}
			}
			else {
				sendMethodNotAllowed(response, HttpMethod.OPTIONS, HttpMethod.GET);
			}
		}
