    public void pushStream(char[] inStream, int inFileId, String name,
                           String inBaseDir, String inEncoding) 
    {
        // store current state in stack
        includeStack.push(new IncludeState(cursor, line, col, fileId,
                                           fileName, baseDir, 
					   encoding, stream) );

        // set new variables
        cursor = 0;
        line = 1;
        col = 1;
        fileId = inFileId;
        fileName = name;
        baseDir = inBaseDir;
        encoding = inEncoding;
        stream = inStream;
    }
