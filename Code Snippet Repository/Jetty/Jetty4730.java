    public void appendString(Appendable buffer, String string)
    {
        if (string == null)
        {
            appendNull(buffer);
            return;
        }

        QuotedStringTokenizer.quote(buffer,string);
    }
