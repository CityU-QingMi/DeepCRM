    String trim(String s) throws IOException
    {
        StringBuilder b = new StringBuilder();
        BufferedReader reader = new BufferedReader(new StringReader(s));

        for (String line = reader.readLine(); line != null; line = reader.readLine())
        {
            if (line.length() > 50)
                line = line.substring(0, 50);
            b.append(line).append('\n');
        }

        return b.toString();
    }
