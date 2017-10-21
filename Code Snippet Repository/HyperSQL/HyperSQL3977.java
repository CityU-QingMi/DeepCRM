    public String describe(Session session) {

        try {
            return describeImpl(session);
        } catch (Throwable e) {
            e.printStackTrace();

            return e.toString();
        }
    }
