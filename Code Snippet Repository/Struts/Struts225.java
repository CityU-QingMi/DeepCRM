  Object createProxy(Class<? super T> expectedType) {
    // TODO: if I create a proxy which implements all the interfaces of
    // the implementation type, I'll be able to get away with one proxy
    // instance (as opposed to one per caller).

    if (!expectedType.isInterface()) {
      throw new DependencyException(expectedType.getName() + " is not an interface.");
    }

    if (invocationHandlers == null) {
      invocationHandlers = new ArrayList<DelegatingInvocationHandler<T>>();
    }

    DelegatingInvocationHandler<T> invocationHandler = new DelegatingInvocationHandler<>();
    invocationHandlers.add(invocationHandler);

    return Proxy.newProxyInstance(
      expectedType.getClassLoader(),
      new Class[] { expectedType },
      invocationHandler
    );
  }
