    private Long getNewLobID() {

        Result result = getNextLobId.execute(sysLobSession);

        if (result.isError()) {
            return Long.valueOf(0);
        }

        RowSetNavigator navigator = result.getNavigator();
        boolean         next      = navigator.next();

        if (!next) {
            navigator.release();

            return Long.valueOf(0);
        }

        Object[] data = navigator.getCurrent();

        navigator.release();

        return (Long) data[0];
    }
