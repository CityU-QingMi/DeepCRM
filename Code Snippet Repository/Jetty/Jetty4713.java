    protected char seekTo(String seek, Source source)
    {
        while (source.hasNext())
        {
            char c = source.peek();
            if (seek.indexOf(c) >= 0)
            {
                return c;
            }

            if (!Character.isWhitespace(c))
                throw new IllegalStateException("Unexpected '" + c + "' while seeking one of '" + seek + "'");
            source.next();
        }

        throw new IllegalStateException("Expected one of '" + seek + "'");
    }
