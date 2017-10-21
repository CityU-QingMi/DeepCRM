    @Override
    public Properties getToolkitProps() {
      Properties props = super.getToolkitProps();
      props.setProperty("rejoin", "true");
      try {
        props.setProperty("tcConfigSnippet", getTestControlMbean().getTsaProxyTcConfig());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      return props;
    }
