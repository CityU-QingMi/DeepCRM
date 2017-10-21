    private String createMultipartRequestString(String filename)
    {
        int length = filename.length();
        String name = filename;
        if (length > 10)
            name = filename.substring(0,10);
        StringBuffer filler = new StringBuffer();
        int i = name.length();
        while (i < 51)
        {
            filler.append("0");
            i++;
        }
        
        return "--AaB03x\r\n"+
        "content-disposition: form-data; name=\"field1\"; filename=\"frooble.txt\"\r\n"+
        "\r\n"+
        "Joe Blow\r\n"+
        "--AaB03x\r\n"+
        "content-disposition: form-data; name=\"stuff\"; filename=\"" + filename + "\"\r\n"+
        "Content-Type: text/plain\r\n"+
        "\r\n"+name+
        filler.toString()+"\r\n" +
        "--AaB03x--\r\n";
    }
