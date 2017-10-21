  private byte[] getBytes(String name) {
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      InputStream in = getClass().getClassLoader().getResourceAsStream(name.replace('.', '/').concat(".class"));
      int b;
      while ((b = in.read()) >= 0) {
        out.write(b);
      }

      return out.toByteArray();
    } catch (IOException ioe) {
      throw new RuntimeException(ioe);
    }
  }
