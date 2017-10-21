    public static String validateProperty(String key, String value,
                                          Object[] meta) {

        if (meta[indexClass].equals("Boolean")) {
            value = value.toLowerCase();

            if (value.equals("true") || value.equals("false")) {
                return null;
            }

            return "invalid boolean value for property: " + key;
        }

        if (meta[indexClass].equals("String")) {
            return null;
        }

        if (meta[indexClass].equals("Long")) {
            return null;
        }

        if (meta[indexClass].equals("Integer")) {
            try {
                int number = Integer.parseInt(value);

                if (Boolean.TRUE.equals(meta[indexIsRange])) {
                    int low  = ((Integer) meta[indexRangeLow]).intValue();
                    int high = ((Integer) meta[indexRangeHigh]).intValue();

                    if (number < low || high < number) {
                        return "value outside range for property: " + key;
                    }
                }

                if (meta[indexValues] != null) {
                    int[] values = (int[]) meta[indexValues];

                    if (ArrayUtil.find(values, number) == -1) {
                        return "value not supported for property: " + key;
                    }
                }
            } catch (NumberFormatException e) {
                return "invalid integer value for property: " + key;
            }

            return null;
        }

        return null;
    }
