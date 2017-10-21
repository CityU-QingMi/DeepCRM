    private static byte decodeHexByte(char hi, char lo)
    {
        try
        {
            return (byte) ((convertHexDigit(hi) << 4) + convertHexDigit(lo));
        }
        catch(NumberFormatException e)
        {
            throw new IllegalArgumentException("Not valid encoding '%" + hi + lo + "'");
        }
    }
