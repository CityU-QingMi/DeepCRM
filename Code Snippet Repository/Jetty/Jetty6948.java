    public void parseQuietly(ByteBuffer buf)
    {
        try (StacklessLogging suppress = new StacklessLogging(Parser.class))
        {
            parse(buf);
        }
        catch (Exception ignore)
        {
            /* ignore */
        }
    }
