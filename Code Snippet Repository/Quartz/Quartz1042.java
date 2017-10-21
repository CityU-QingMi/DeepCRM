  @Override
  public void onClusterEvent(ClusterEvent event) {
    switch (event.getType()) {
      case NODE_JOINED:
      case OPERATIONS_DISABLED:
      case OPERATIONS_ENABLED:
        break;
      case NODE_LEFT:
        getLog().info("Received node left notification for " + event.getNode().getId());
        nodeLeft(event);
        break;
      case NODE_REJOINED:
        getLog().info("Received rejoin notification " + terracottaClientId + " => " + event.getNode().getId());
        terracottaClientId = event.getNode().getId();
        break;
    }
  }
