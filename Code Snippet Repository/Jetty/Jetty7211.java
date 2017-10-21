    private void attributes(Map<String,String> attributes) throws IOException
    {
        for (String k:attributes.keySet())
        {
            String v = attributes.get(k);
            _out.append(' ').append(k).append("=\"");
            content(v);
            _out.append('"');
        }
    }
