    protected static void complete(String seek, Source source)
    {
        int i = 0;
        while (source.hasNext() && i < seek.length())
        {
            char c = source.next();
            if (c != seek.charAt(i++))
                throw new IllegalStateException("Unexpected '" + c + " while seeking  \"" + seek + "\"");
        }

        if (i < seek.length())
            throw new IllegalStateException("Expected \"" + seek + "\"");
    }
