		@Override
		public void handle(ServerHttpRequest request, ServerHttpResponse response) throws IOException {
			if (!HttpMethod.GET.equals(request.getMethod())) {
				sendMethodNotAllowed(response, HttpMethod.GET);
				return;
			}

			String content = String.format(IFRAME_CONTENT, getSockJsClientLibraryUrl());
			byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
			StringBuilder builder = new StringBuilder("\"0");
			DigestUtils.appendMd5DigestAsHex(contentBytes, builder);
			builder.append('"');
			String etagValue = builder.toString();

			List<String> ifNoneMatch = request.getHeaders().getIfNoneMatch();
			if (!CollectionUtils.isEmpty(ifNoneMatch) && ifNoneMatch.get(0).equals(etagValue)) {
				response.setStatusCode(HttpStatus.NOT_MODIFIED);
				return;
			}

			response.getHeaders().setContentType(new MediaType("text", "html", StandardCharsets.UTF_8));
			response.getHeaders().setContentLength(contentBytes.length);

			// No cache in order to check every time if IFrame are authorized
			addNoCacheHeaders(response);
			response.getHeaders().setETag(etagValue);
			response.getBody().write(contentBytes);
		}
