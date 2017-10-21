    @Override
    public Map<String, JPanel> getTabs() {
        try {
            final Client client = new Client(getContext().getMBeanServerConnection());
            final ClientGui gui = new ClientGui(client);
            final Map<String, JPanel> result = new HashMap<>();
            result.put("Log4j2", gui);
            return result;
        } catch (final Throwable ex) {
            throw new IllegalStateException(ex);
        }
    }
