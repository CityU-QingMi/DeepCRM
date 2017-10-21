  public static String serializeToString(Object key) throws IOException {
    if (key instanceof String) {
      String stringKey = (String) key;

      // disallow Strings that start with our marker
      if (stringKey.length() >= 1) {
        if (stringKey.charAt(0) == MARKER) {
          //
          throw new IOException("Illegal string key: " + stringKey);
        }

      }

      return stringKey;
    }

    StringSerializedObjectOutputStream out = new StringSerializedObjectOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(out);
    writeStringKey(key, oos);
    oos.close();

    return out.toString();
  }
