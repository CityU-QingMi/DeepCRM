    public void printMultiLn(String s) {
        int index = 0;

        // look for hidden newlines inside strings
        while ((index = s.indexOf('\n', index)) > -1) {
            javaLine++;
            index++;
        }

        writer.print(s);
    }
