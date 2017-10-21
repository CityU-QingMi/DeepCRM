    protected void writeNull(Type type) {

        if (!noSeparators) {
            if (logMode == MODE_DELETE) {
                write(BYTES_IS);
            } else if (isWritten) {
                write(',');
            }

            isWritten = true;
        }

        write(BYTES_NULL);
    }
