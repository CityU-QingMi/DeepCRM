    private String createRequestString(String body)
    {
        StringBuilder req1 = new StringBuilder();
        req1.append("POST /chat/ HTTP/1.1\r\n");
        req1.append("Host: tester\r\n");
        req1.append("Content-length: " + body.length() + "\r\n");
        req1.append("Content-type: application/x-www-form-urlencoded\r\n");
        req1.append("Connection: close\r\n");
        req1.append("\r\n");
        req1.append(body);
        return req1.toString();
    }
