  Scope.Strategy getScopeStrategy() {
    if (scopeStrategy == null) {
      scopeStrategy = (Scope.Strategy) container.localScopeStrategy.get();

      if (scopeStrategy == null) {
        throw new IllegalStateException("Scope strategy not set. Please call Container.setScopeStrategy().");
      }
    }

    return scopeStrategy;
  }
