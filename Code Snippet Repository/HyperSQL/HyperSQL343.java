    private void reload(boolean increaseBuffer) throws IOException {
        if (reader == null)
            throw new IllegalStateException(
                    "Attempt to reload after source file has been closed");
        if (increaseBuffer) charBuffer = new char[charBuffer.length * 2];
        //if (increaseBuffer) System.err.println("-> " + charBuffer.length);
        int retVal = reader.read(charBuffer);
        // Indicate OED for 0, since we could get into loop by returning 0:
        if (retVal > 0)
            stringBuffer.append(charBuffer, 0, retVal);
        else
            close();
    }
