    protected XStream createXStream(ActionInvocation invocation) {
        XStream stream = new XStream();
        LOG.debug("Clears existing permissions");
        stream.addPermission(NoTypePermission.NONE);

        LOG.debug("Adds per action permissions");
        addPerActionPermission(invocation, stream);

        LOG.debug("Adds default permissions");
        addDefaultPermissions(invocation, stream);
        return stream;
    }
