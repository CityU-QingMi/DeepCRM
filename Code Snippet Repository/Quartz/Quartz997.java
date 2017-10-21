    @Override
    public void addSchedulerProperties(Properties properties) {
      super.addSchedulerProperties(properties);
      properties.remove(AbstractTerracottaJobStore.TC_CONFIGURL_PROP);
      try {
        properties.setProperty(AbstractTerracottaJobStore.TC_CONFIG_PROP, getTestControlMbean().getTsaProxyTcConfig());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      properties.setProperty(AbstractTerracottaJobStore.TC_REJOIN_PROP, "true");
    }
