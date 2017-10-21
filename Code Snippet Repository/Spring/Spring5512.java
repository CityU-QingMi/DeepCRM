	@Test(expected = IllegalStateException.class)
	public void findAvailableUdpPortWhenPortOnLoopbackInterfaceIsNotAvailable() throws Exception {
		int port = SocketUtils.findAvailableUdpPort();
		DatagramSocket socket = new DatagramSocket(port, InetAddress.getByName("localhost"));
		try {
			// will only look for the exact port
			SocketUtils.findAvailableUdpPort(port, port);
		}
		finally {
			socket.close();
		}
	}
