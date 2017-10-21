    int nextChar() throws JasperException {
        if (!hasMoreInput())
            return -1;
        
        int ch = current.stream[current.cursor];

        current.cursor++;
        
        if (ch == '\n') {
            current.line++;
            current.col = 0;
        } else {
            current.col++;
        }
        return ch;
    }
