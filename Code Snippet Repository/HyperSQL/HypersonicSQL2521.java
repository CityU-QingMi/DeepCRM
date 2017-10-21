    public int getPropertyWithinRange(String name, int number) {

        Object[] meta = (Object[]) metaData.get(name);

        if (meta == null) {
            return number;
        }

        if (meta[indexClass].equals("Integer")) {
            if (Boolean.TRUE.equals(meta[indexIsRange])) {
                int low  = ((Integer) meta[indexRangeLow]).intValue();
                int high = ((Integer) meta[indexRangeHigh]).intValue();

                if (number < low) {
                    return low;
                } else if (high < number) {
                    return high;
                }
            }

            if (meta[indexValues] != null) {
                int[] values = (int[]) meta[indexValues];

                if (ArrayUtil.find(values, number) == -1) {
                    return values[0];
                }
            }
        }

        return number;
    }
