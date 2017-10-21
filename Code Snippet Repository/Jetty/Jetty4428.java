        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            //test that the multipart content bytes were converted correctly from their charset to unicode
            String filename = req.getParameter("ttt");
            assertEquals("ttt.txt",filename);  
            String contentType = (String)req.getParameter("ttt"+MultiPartFilter.CONTENT_TYPE_SUFFIX);
            assertEquals("application/octet-stream; charset=UTF-8",contentType);  
            String charset=MimeTypes.getCharsetFromContentType(contentType);
            assertEquals("utf-8",charset);  
            
            File file = (File)req.getAttribute("ttt");
            String content=IO.toString(new InputStreamReader(new FileInputStream(file),charset));
            assertEquals("ttt\u01FCzzz",content);       
            
            resp.setStatus(200);
            resp.setContentType(contentType);
            resp.getWriter().print(content);
        } 
