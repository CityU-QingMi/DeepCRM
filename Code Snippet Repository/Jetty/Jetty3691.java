    @Parameters
    public static List<Object[]> data()
    {
        List<Object[]> data = new ArrayList<>();
        
        data.add(new String[]{ "GET /bad VER\r\n"
                + "Host: localhost\r\n"
                + "Connection: close\r\n\r\n" , 
                "GET <invalidrequest> HTTP/1.1 400" });
        data.add(new String[]{ "GET /%%adsasd HTTP/1.1\r\n"
                + "Host: localhost\r\n"
                + "Connection: close\r\n\r\n" , 
                "GET <invalidrequest> HTTP/1.1 400" });
        
        return data;
    }
