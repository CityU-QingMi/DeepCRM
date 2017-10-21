    protected void handleForm(HttpServletRequest request,
                          HttpServletResponse response)
    {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("Action");
        String name =  request.getParameter("Name");
        String value =  request.getParameter("Value");

        if (action!=null)
        {
            if(action.equals("New Session"))
            {
                session = request.getSession(true);
                session.setAttribute("test","value");
                session.setAttribute("obj", new ObjectAttributeValue(System.currentTimeMillis()));
            }
            else if (session!=null)
            {
                if (action.equals("Invalidate"))
                    session.invalidate();
                else if (action.equals("Set") && name!=null && name.length()>0)
                    session.setAttribute(name,value);
                else if (action.equals("Remove"))
                    session.removeAttribute(name);
            }
        }
    }
