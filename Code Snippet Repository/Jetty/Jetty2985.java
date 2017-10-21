    public HttpField getDateField()
    {
        long now=System.currentTimeMillis();
        long seconds = now/1000;
        DateField df = _dateField;

        if (df==null || df._seconds!=seconds)
        {
            try(Locker.Lock lock = _dateLocker.lock())
            {
                df = _dateField;
                if (df==null || df._seconds!=seconds)
                {
                    HttpField field=new PreEncodedHttpField(HttpHeader.DATE,DateGenerator.formatDate(now));
                    _dateField=new DateField(seconds,field);
                    return field;
                }
            }
        }
        return df._dateField;
    }
