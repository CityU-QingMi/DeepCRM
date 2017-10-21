    public RowSetNavigatorData(Session session, RowSetNavigator navigator) {

        this.session = session;

        setCapacity(navigator.size);

        while (navigator.next()) {
            add(navigator.getCurrent());
        }
    }
