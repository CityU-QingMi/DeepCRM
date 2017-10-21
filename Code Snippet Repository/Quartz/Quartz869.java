    @Override
    public void initialize() throws SchedulerException {
        InitialContext ctx = null;
        try {
            ctx = new InitialContext(getContextProperties());
            server = (MBeanServerConnection)ctx.lookup(RMI_ADAPTOR_JNDI_NAME);
        } catch (Exception e) {
            throw new SchedulerException("Failed to lookup JBoss JMX RMI Adaptor.", e);
        } finally {
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (NamingException ignore) {
                }
            }
        }
    }
