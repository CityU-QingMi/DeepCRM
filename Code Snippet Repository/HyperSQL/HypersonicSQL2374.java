    public RowSetNavigatorDataTable(Session session,
                                    QuerySpecification select,
                                    RowSetNavigatorData navigator) {

        this(session, select);

        navigator.reset();

        while (navigator.next()) {
            add(navigator.getCurrent());
        }
    }
