    public List<String> getCSV(HttpHeader header,boolean keepQuotes)
    {
        QuotedCSV values = null;
        for (HttpField f : this)
        {
            if (f.getHeader()==header)
            {
                if (values==null)
                    values = new QuotedCSV(keepQuotes);
                values.addValue(f.getValue());
            }
        }
        return values==null?Collections.emptyList():values.getValues();
    }
