	@Test(expected = IllegalStateException.class)
	public void findAvailableTcpPortWhenPortOnLoopbackInterfaceIsNotAvailable() throws Exception {
		int port = SocketUtils.findAvailableTcpPort();
		ServerSocket socket = ServerSocketFactory.getDefault().createServerSocket(port, 1, InetAddress.getByName("localhost"));
		try {
			SocketUtils.findAvailableTcpPort(port, port);
		}
		finally {
			socket.close();
		}
	}
