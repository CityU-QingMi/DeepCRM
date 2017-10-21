    protected void handleForm(HttpServletRequest request,
                          HttpServletResponse response)
    {
        String name =  request.getParameter("Name");
        String value =  request.getParameter("Value");
        String age =  request.getParameter("Age");

        if (name!=null && name.length()>0)
        {
            Cookie cookie = new Cookie(name,value);
            if (age!=null && age.length()>0)
                cookie.setMaxAge(Integer.parseInt(age));
            response.addCookie(cookie);
        }
    }
