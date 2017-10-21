    private static char decodeHexChar(int hi, int lo)
    {
        try
        {
            return (char) ((convertHexDigit(hi) << 4) + convertHexDigit(lo));
        }
        catch(NumberFormatException e)
        {
            throw new IllegalArgumentException("Not valid encoding '%" + (char) hi + (char) lo + "'");
        }
    }
