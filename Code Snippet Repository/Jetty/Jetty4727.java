    public void appendArray(Appendable buffer, Object array)
    {
        try
        {
            if (array == null)
            {
                appendNull(buffer);
                return;
            }

            buffer.append('[');
            int length = Array.getLength(array);

            for (int i = 0; i < length; i++)
            {
                if (i != 0)
                    buffer.append(',');
                append(buffer,Array.get(array,i));
            }

            buffer.append(']');
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
