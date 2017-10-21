  public void stress(int num) {
    List<Loader> loaders = new ArrayList<Loader>();
    String name = PermStress.class.getName();
    byte[] clazz = getBytes(name);

    for (int i = 0; i < num; i++) {
      try {
        Loader loader = new Loader();
        loaders.add(loader);
        loader.defineClass(name, clazz);
      } catch (OutOfMemoryError error) {
        loaders.clear();
        error.printStackTrace();
        break;
      }
    }
  }
