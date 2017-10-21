	@Override
	public void afterPropertiesSet() throws IllegalArgumentException {
		Assert.notNull(this.type, "Property 'type' is required");
		Assert.notNull(this.hostname, "Property 'hostname' is required");
		if (this.port < 0 || this.port > 65535) {
			throw new IllegalArgumentException("Property 'port' value out of range: " + this.port);
		}

		SocketAddress socketAddress = new InetSocketAddress(this.hostname, this.port);
		this.proxy = new Proxy(this.type, socketAddress);
	}
