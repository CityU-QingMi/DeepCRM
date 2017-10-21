    protected void writeFieldType(Type type) {

        if (!noSeparators) {
            if (logMode == MODE_DELETE) {
                write('=');
            } else if (isWritten) {
                write(',');
            }

            isWritten = true;
        }
    }
