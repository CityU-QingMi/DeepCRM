    public Mark popStream() {
        // make sure we have something to pop
        if ( includeStack.size() <= 0 ) {
            return null;
        }

        // get previous state in stack
        IncludeState state = (IncludeState) includeStack.pop( );

        // set new variables
        cursor = state.cursor;
        line = state.line;
        col = state.col;
        fileId = state.fileId;
        fileName = state.fileName;
        baseDir = state.baseDir;
        stream = state.stream;
        return this;
    }
