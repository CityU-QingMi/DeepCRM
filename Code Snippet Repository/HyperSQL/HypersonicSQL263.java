    int getLength(SessionInterface session, Object o) {

        int l;

        if (o instanceof LobData) {
            l = (int) ((LobData) o).length(session);
        } else {
            l = ((String) o).length();
        }

        return l;
    }
