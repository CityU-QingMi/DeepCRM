	private static URI resolveBaseUrl(HttpServerRequest request) throws URISyntaxException {
		String scheme = getScheme(request);
		String header = request.requestHeaders().get(HttpHeaderNames.HOST);
		if (header != null) {
			final int portIndex;
			if (header.startsWith("[")) {
				portIndex = header.indexOf(':', header.indexOf(']'));
			}
			else {
				portIndex = header.indexOf(':');
			}
			if (portIndex != -1) {
				try {
					return new URI(scheme, null, header.substring(0, portIndex),
							Integer.parseInt(header.substring(portIndex + 1)), null, null, null);
				}
				catch (NumberFormatException ex) {
					throw new URISyntaxException(header, "Unable to parse port", portIndex);
				}
			}
			else {
				return new URI(scheme, header, null, null);
			}
		}
		else {
			InetSocketAddress localAddress = (InetSocketAddress) request.context().channel().localAddress();
			return new URI(scheme, null, localAddress.getHostString(),
					localAddress.getPort(), null, null, null);
		}
	}
