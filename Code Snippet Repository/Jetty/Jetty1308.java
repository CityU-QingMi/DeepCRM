    @Deprecated
    public static List<String> qualityList(Enumeration<String> e)
    {
        if (e == null || !e.hasMoreElements())
            return Collections.emptyList();

        QuotedQualityCSV values = new QuotedQualityCSV();
        while(e.hasMoreElements())
            values.addValue(e.nextElement());
        return values.getValues();
    }
