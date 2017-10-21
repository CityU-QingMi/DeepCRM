    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        byte[] buffer = new byte[8192];
        int len=request.getContentLength();
        int c=0;
        InputStream in=request.getInputStream();
        while (c<len)
        {
            int l = in.read(buffer);
            if (l<0)
                break;
            c+=l;
        }
        request.setAttribute("PUT",c+"bytes");
        doGet(request, response);
    }
