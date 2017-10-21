    private void assertValidPath(Path path)
    {
        // TODO merged from 9.2, check if necessary
        String str = path.toString();
        int idx = StringUtil.indexOfControlChars(str);
        if(idx >= 0)
        {
            throw new InvalidPathException(str, "Invalid Character at index " + idx);
        }
    }
