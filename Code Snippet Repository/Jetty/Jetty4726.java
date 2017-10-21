    public void appendArray(Appendable buffer, Collection collection)
    {
        try
        {
            if (collection == null)
            {
                appendNull(buffer);
                return;
            }

            buffer.append('[');
            Iterator iter = collection.iterator();
            boolean first = true;
            while (iter.hasNext())
            {
                if (!first)
                    buffer.append(',');

                first = false;
                append(buffer,iter.next());
            }

            buffer.append(']');
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
