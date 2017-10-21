    @Override
    public String read() throws IOException {
        String message = Strings.EMPTY;
        try {
            while (true) {
                final int b = inputStream.read();
                if (b == -1) {
                    throw new EOFException("The stream has been closed or the end of stream has been reached");
                }
                buffer.write(b);
                if (b == '\n') {
                    break;
                }
            }
        }
        catch (final EOFException e) {
            if (buffer.size() > 0) {
                message = buffer.toString();
                buffer.reset();
                return message;
            }
            throw e;
        }
        message = buffer.toString();
        buffer.reset();
        return message;
    }
