    public boolean validateProperty(String name, int number) {

        Object[] meta = (Object[]) metaData.get(name);

        if (meta == null) {
            return false;
        }

        if (meta[indexClass].equals("Integer")) {
            if (Boolean.TRUE.equals(meta[indexIsRange])) {
                int low  = ((Integer) meta[indexRangeLow]).intValue();
                int high = ((Integer) meta[indexRangeHigh]).intValue();

                if (number < low || high < number) {
                    return false;
                }
            }

            if (meta[indexValues] != null) {
                int[] values = (int[]) meta[indexValues];

                if (ArrayUtil.find(values, number) == -1) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }
