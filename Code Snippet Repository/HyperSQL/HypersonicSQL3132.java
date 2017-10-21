    protected boolean allowConnection(Socket socket) {

        if (isShuttingDown) {
            return false;
        }

        return (acl == null) ? true
                             : acl.permitAccess(
                                 socket.getInetAddress().getAddress());
    }
