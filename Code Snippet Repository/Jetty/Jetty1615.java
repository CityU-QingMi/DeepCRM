    @Override
    public void upgrade(Connection newConnection)
    {
        Connection old_connection = getConnection();

        if (LOG.isDebugEnabled())
            LOG.debug("{} upgrading from {} to {}", this, old_connection, newConnection);

        ByteBuffer prefilled = (old_connection instanceof Connection.UpgradeFrom)
                ?((Connection.UpgradeFrom)old_connection).onUpgradeFrom():null;
        old_connection.onClose();
        old_connection.getEndPoint().setConnection(newConnection);

        if (newConnection instanceof Connection.UpgradeTo)
            ((Connection.UpgradeTo)newConnection).onUpgradeTo(prefilled);
        else if (BufferUtil.hasContent(prefilled))
            throw new IllegalStateException();

        newConnection.onOpen();
    }
