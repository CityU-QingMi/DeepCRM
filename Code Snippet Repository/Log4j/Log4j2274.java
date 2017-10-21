        @Override
        public void run() {
            ServerSocket server = null;
            client = null;
            try {
                server = new ServerSocket(SOCKET_PORT);
                client = server.accept();
                while (!shutdown) {
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    final String line = reader.readLine();
                    if (line.equals("Shutdown")) {
                        shutdown = true;
                    } else {
                        list.add(line);
                    }
                }
            } catch (final Exception ex) {
                ex.printStackTrace();
            } finally {
                if (client != null) {
                    try {
                        client.close();
                    } catch (final Exception ex) {
                        System.out.println("Unable to close socket " + ex.getMessage());
                    }
                }
                if (server != null) {
                    try {
                        server.close();
                    } catch (final Exception ex) {
                        System.out.println("Unable to close server socket " + ex.getMessage());
                    }
                }
            }
        }
