    public ClientGui(final Client client) throws IOException, JMException {
        this.client = Objects.requireNonNull(client, "client");
        createWidgets();
        populateWidgets();

        // register for Notifications if LoggerContext MBean was added/removed
        final ObjectName addRemoveNotifs = MBeanServerDelegate.DELEGATE_NAME;
        final NotificationFilterSupport filter = new NotificationFilterSupport();
        filter.enableType(Server.DOMAIN); // only interested in Log4J2 MBeans
        client.getConnection().addNotificationListener(addRemoveNotifs, this, null, null);
    }
