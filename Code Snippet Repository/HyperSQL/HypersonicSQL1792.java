    public JDBCXAConnectionWrapper(JDBCXAResource xaResource,
                                   JDBCXAConnection xaConnection,
                                   JDBCConnection databaseConnection)
                                   throws SQLException {
        // todo: Review JDBCXADataSource and this class.
        //       Under current implementation, because we do not pass a
        //       JDBCXAConnection instance to the constructor to pick
        //       up the connectionClosed event listener callback, calling
        //       close() on this wrapper connection does not reset the
        //       physical connection or set the inUse flag to false until
        //       the vending JDBCXAConnection itself is closed, which marks
        //       the end of its useful life.
        //
        //       In other words, due to this current implementation detail,
        //       JDBCXADataSource cannot cooperate with a pooling implementation
        //       to reuse physical connections.
        //       fixed - the event listener works
        super(databaseConnection, xaConnection);

        xaResource.setConnection(this);

        this.xaResource = xaResource;
    }
