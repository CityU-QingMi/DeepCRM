    private String toCSV (Resource[] resources)
    {
        StringBuffer rb = new StringBuffer();

        for (Resource r:resources)
        {
            if (rb.length() > 0) rb.append(",");
            rb.append(r.toString());
        }        

        return rb.toString();
    }
