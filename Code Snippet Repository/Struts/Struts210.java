    private Object doConvertToCalendar(Map<String, Object> context, Object value) {
        Object result = null;
        Date dateResult = (Date) doConvertToDate(context, value, Date.class);
        if (dateResult != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateResult);
            result = calendar;
        }
        return result;
    }
