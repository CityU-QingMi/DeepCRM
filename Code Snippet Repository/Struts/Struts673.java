        private String[] toStringArray() {
            if (value == null) {
                LOG.trace("The value is null, empty array of string will be returned!");
                return new String[]{};
            } else if (value.getClass().isArray()) {
                LOG.trace("Converting value {} to array of strings", value);

                Object[] values = (Object[]) value;
                String[] strValues = new String[values.length];
                int i = 0;
                for (Object v : values) {
                    strValues[i] = Objects.toString(v, null);
                    i++;
                }
                return strValues;
            } else {
                LOG.trace("Converting value {} to simple string", value);
                return new String[]{ value.toString() };
            }
        }
