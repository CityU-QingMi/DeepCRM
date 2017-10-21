    private DataFrame toDataFrame(byte op)
    {
        switch (op)
        {
            case OpCode.BINARY:
                return new BinaryFrame();
            case OpCode.TEXT:
                return new TextFrame();
            case OpCode.CONTINUATION:
                return new ContinuationFrame();
            default:
                throw new IllegalArgumentException("Not a data frame: " + op);
        }
    }
