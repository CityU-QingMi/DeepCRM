    public void write(String str, boolean noIndent) throws IOException {
        if (!noIndent) {
            str = "    " + str;
        }

        if (writer instanceof IndentWriter) {
            ((IndentWriter) writer).write(str, false);
        } else {
            writer.write(str + "\n");
        }
    }
