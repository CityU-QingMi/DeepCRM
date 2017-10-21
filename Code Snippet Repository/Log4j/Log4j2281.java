    private void closeSockets() {
        if(clientSocket != null) {
            try {
                clientSocket.close();
            }
            catch(final Exception e) {}
        }
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (final Exception e) {
            }
        }
    }
