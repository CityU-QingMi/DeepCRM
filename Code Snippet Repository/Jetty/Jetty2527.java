    @Override
    public String apply(String target, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // Check that cookie is not already set
        Cookie[] cookies = request.getCookies();
        if (cookies!=null)
        {
            for (Cookie cookie:cookies)
            {
                if (_name.equals(cookie.getName()) && _value.equals(cookie.getValue()))
                    return target;
            }
        }
        
        // set it
        response.addCookie(new Cookie(_name, _value));
        return target;
    }
