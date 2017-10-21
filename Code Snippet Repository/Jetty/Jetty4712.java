    protected void seekTo(char seek, Source source)
    {
        while (source.hasNext())
        {
            char c = source.peek();
            if (c == seek)
                return;

            if (!Character.isWhitespace(c))
                throw new IllegalStateException("Unexpected '" + c + " while seeking '" + seek + "'");
            source.next();
        }

        throw new IllegalStateException("Expected '" + seek + "'");
    }
