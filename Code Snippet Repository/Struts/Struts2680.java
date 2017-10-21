    public char readChar() throws java.io.IOException {
        if (inBuf > 0) {
            --inBuf;

            if (++bufpos == bufsize)
                bufpos = 0;

            return buffer[bufpos];
        }

        if (++bufpos >= maxNextCharInd)
            FillBuff();

        char c = buffer[bufpos];

        UpdateLineColumn(c);
        return c;
    }
