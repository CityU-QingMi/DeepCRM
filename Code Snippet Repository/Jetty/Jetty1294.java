    public List<String> getQualityCSV(HttpHeader header)
    {
        QuotedQualityCSV values = null;
        for (HttpField f : this)
        {
            if (f.getHeader()==header)
            {
                if (values==null)
                    values = new QuotedQualityCSV();
                values.addValue(f.getValue());
            }
        }

        return values==null?Collections.emptyList():values.getValues();
    }
