		@Override
		public void connect(String host, int port, String username, String password) throws MessagingException {
			if (host == null) {
				throw new MessagingException("no host");
			}
			this.connectedHost = host;
			this.connectedPort = port;
			this.connectedUsername = username;
			this.connectedPassword = password;
			setConnected(true);
		}
