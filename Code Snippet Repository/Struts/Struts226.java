    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
      if (delegate == null) {
        throw new IllegalStateException(
            "Not finished constructing. Please don't call methods on this"
                + " object until the caller's construction is complete.");
      }

      try {
        return method.invoke(delegate, args);
      } catch (IllegalAccessException | IllegalArgumentException e) {
        throw new RuntimeException(e);
      } catch (InvocationTargetException e) {
        throw e.getTargetException();
      }
    }
