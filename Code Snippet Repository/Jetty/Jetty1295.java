    public List<String> getQualityCSV(String name)
    {
        QuotedQualityCSV values = null;
        for (HttpField f : this)
        {
            if (f.getName().equalsIgnoreCase(name))
            {
                if (values==null)
                    values = new QuotedQualityCSV();
                values.addValue(f.getValue());
            }
        }
        return values==null?Collections.emptyList():values.getValues();
    }
