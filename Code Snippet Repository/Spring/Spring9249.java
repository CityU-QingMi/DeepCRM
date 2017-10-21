	private FullHttpRequest createFullHttpRequest(HttpHeaders headers) {
		io.netty.handler.codec.http.HttpMethod nettyMethod =
				io.netty.handler.codec.http.HttpMethod.valueOf(this.method.name());

		String authority = this.uri.getRawAuthority();
		String path = this.uri.toString().substring(this.uri.toString().indexOf(authority) + authority.length());
		FullHttpRequest nettyRequest = new DefaultFullHttpRequest(
				HttpVersion.HTTP_1_1, nettyMethod, path, this.body.buffer());

		nettyRequest.headers().set(HttpHeaders.HOST, this.uri.getHost() + ":" + getPort(uri));
		nettyRequest.headers().set(HttpHeaders.CONNECTION, "close");
		for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
			nettyRequest.headers().add(entry.getKey(), entry.getValue());
		}
		if (!nettyRequest.headers().contains(HttpHeaders.CONTENT_LENGTH) && this.body.buffer().readableBytes() > 0) {
			nettyRequest.headers().set(HttpHeaders.CONTENT_LENGTH, this.body.buffer().readableBytes());
		}

		return nettyRequest;
	}
