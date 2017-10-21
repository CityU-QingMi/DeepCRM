    @Override
    public List<HttpCookie> getCookies()
    {
        if(cookies == null)
        {
            Cookie[] requestCookies = request.getCookies();
            if (requestCookies != null)
            {
                cookies = new ArrayList<>();
                for (Cookie requestCookie : requestCookies)
                {
                    HttpCookie cookie = new HttpCookie(requestCookie.getName(), requestCookie.getValue());
                    // No point handling domain/path/expires/secure/httponly on client request cookies
                    cookies.add(cookie);
                }
            }
        }
        
        return cookies;
    }
