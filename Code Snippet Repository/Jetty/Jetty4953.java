    public void deleteParts ()
    throws MultiException
    {
        Collection<Part> parts = getParsedParts();
        MultiException err = new MultiException();
        for (Part p:parts)
        {
            try
            {
                ((MultiPartInputStreamParser.MultiPart)p).cleanUp();
            }
            catch(Exception e)
            {
                err.add(e);
            }
        }
        _parts.clear();

        err.ifExceptionThrowMulti();
    }
