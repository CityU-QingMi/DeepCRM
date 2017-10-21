	@Override
	public void handle(HttpExchange exchange) throws IOException {
		try {
			RemoteInvocation invocation = readRemoteInvocation(exchange);
			RemoteInvocationResult result = invokeAndCreateResult(invocation, getProxy());
			writeRemoteInvocationResult(exchange, result);
			exchange.close();
		}
		catch (ClassNotFoundException ex) {
			exchange.sendResponseHeaders(500, -1);
			logger.error("Class not found during deserialization", ex);
		}
	}
