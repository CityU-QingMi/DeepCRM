    public static HsqlProperties argArrayToProps(String[] arg, String type) {

        HsqlProperties props = new HsqlProperties();

        for (int i = 0; i < arg.length; i++) {
            String p = arg[i];

            if (p.equals("--help") || p.equals("-help")) {
                props.addError(NO_VALUE_FOR_KEY, p.substring(1));
            } else if (p.startsWith("--")) {
                String value = i + 1 < arg.length ? arg[i + 1]
                                                  : "";

                props.setProperty(type + "." + p.substring(2), value);

                i++;
            } else if (p.charAt(0) == '-') {
                String value = i + 1 < arg.length ? arg[i + 1]
                                                  : "";

                props.setProperty(type + "." + p.substring(1), value);

                i++;
            }
        }

        return props;
    }
