    private void setLineNumber(int c) {

        if (c == '\r' || c == '\n') {
            if (currentPosition == eolPosition + 1) {
                if (c == '\n' && eolCode != c) {

                    //
                } else {
                    lineNumber++;
                }
            } else {
                lineNumber++;
            }

            eolPosition = currentPosition;
            eolCode     = c;
        }
    }
