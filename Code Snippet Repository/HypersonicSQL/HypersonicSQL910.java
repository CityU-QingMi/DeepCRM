    public synchronized boolean isUserActive(String userName) {

        Iterator it = sessionMap.values().iterator();

        for (int i = 0; it.hasNext(); i++) {
            Session session = (Session) it.next();

            if (!session.isClosed()
                    && userName.equals(
                        session.getUser().getName().getNameString())) {
                return true;
            }
        }

        return false;
    }
