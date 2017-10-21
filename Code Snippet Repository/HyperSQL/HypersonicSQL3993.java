    private static void init() {

        directives     = new Hashtable();
        labels         = new String[17];
        Field[] fields = LineType.class.getDeclaredFields();

        for (int i = 0, j = 0; i < fields.length; i++) {
            Field field = fields[i];

            if (field.getType().equals(Integer.TYPE)) {
                String label = field.getName();

                try {
                    int value = field.getInt(null);

                    labels[value] = label;

                    switch(value) {
                        case VISIBLE :
                        case HIDDEN : {
                            // ignore
                            break;
                        }
                        default : {
                            String key = Line.DIRECTIVE_PREFIX
                                    + label.toLowerCase(Locale.ENGLISH);

                            directives.put(key, new Integer(value));

                            break;
                        }
                    }

                } catch (IllegalArgumentException ex) {
                    // ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    // ex.printStackTrace();
                }
            }
        }
    }
