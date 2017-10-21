    @Override
    public Object convertValue(Map<String, Object> context, Object target, Member member, String propertyName, Object value, Class toType) {
        String result;

        if (value.getClass().isArray()) {
            int length = Array.getLength(value);
            List<String> converted = new ArrayList<>(length);

            for (int i = 0; i < length; i++) {
                Object o = Array.get(value, i);
                converted.add(convertToString(getLocale(context), o));
            }

            result = StringUtils.join(converted, ", ");
        } else if(value.getClass().isAssignableFrom(Collection.class)) {
            Collection<?> colValue = (Collection) value;
            List<String> converted = new ArrayList<>(colValue.hashCode());

            for (Object o : colValue) {
                converted.add(convertToString(getLocale(context), o));
            }

            result = StringUtils.join(converted, ", ");
        } else if (value instanceof Date) {
            DateFormat df;
            if (value instanceof java.sql.Time) {
                df = DateFormat.getTimeInstance(DateFormat.MEDIUM, getLocale(context));
            } else if (value instanceof java.sql.Timestamp) {
                SimpleDateFormat dfmt = (SimpleDateFormat) DateFormat.getDateTimeInstance(DateFormat.SHORT,
                        DateFormat.MEDIUM,
                        getLocale(context));
                df = new SimpleDateFormat(dfmt.toPattern() + MILLISECOND_FORMAT);
            } else {
                df = DateFormat.getDateInstance(DateFormat.SHORT, getLocale(context));
            }
            result = df.format(value);
        } else {
            result = convertToString(getLocale(context), value);
        }

        return result;
    }
