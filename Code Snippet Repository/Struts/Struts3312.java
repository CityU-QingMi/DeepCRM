    protected void date(Date date, Method method) {
        JSON json = null;
        if (method != null)
            json = method.getAnnotation(JSON.class);
        if (this.formatter == null)
            this.formatter = new SimpleDateFormat(JSONUtil.RFC3339_FORMAT);

        DateFormat formatter = (json != null) && (json.format().length() > 0) ? new SimpleDateFormat(json
                .format()) : this.formatter;
        this.string(formatter.format(date));
    }
