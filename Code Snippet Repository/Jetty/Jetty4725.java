    public void appendMap(Appendable buffer, Map<?,?> map)
    {
        try
        {
            if (map == null)
            {
                appendNull(buffer);
                return;
            }

            buffer.append('{');
            Iterator<?> iter = map.entrySet().iterator();
            while (iter.hasNext())
            {
                Map.Entry<?,?> entry = (Map.Entry<?,?>)iter.next();
                QuotedStringTokenizer.quote(buffer,entry.getKey().toString());
                buffer.append(':');
                append(buffer,entry.getValue());
                if (iter.hasNext())
                    buffer.append(',');
            }

            buffer.append('}');
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
