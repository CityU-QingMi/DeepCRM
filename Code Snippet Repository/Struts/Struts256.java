  @Deprecated
  public static String capitalize(String s) {
    if (s.length() == 0)
      return s;
    char first = s.charAt(0);
    char capitalized = Character.toUpperCase(first);
    return (first == capitalized)
        ? s
        : capitalized + s.substring(1);
  }
