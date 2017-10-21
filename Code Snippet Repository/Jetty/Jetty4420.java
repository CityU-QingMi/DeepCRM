        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            //we have configured the multipart filter to always store to disk (file size threshold == 1)
            //but fileName has no filename param, so only the attribute should be set
            assertNull(req.getParameter("fileName"));
            assertNotNull(req.getAttribute("fileName"));
            File f = (File)req.getAttribute("fileName");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IO.copy(new FileInputStream(f), baos);
            assertEquals(getServletContext().getAttribute("fileName"), baos.toString());
            assertNull(req.getParameter("desc"));
            assertNotNull(req.getAttribute("desc"));
            baos = new ByteArrayOutputStream();
            IO.copy(new FileInputStream((File)req.getAttribute("desc")), baos);
            assertEquals(getServletContext().getAttribute("desc"), baos.toString());
            assertNull(req.getParameter("title"));
            assertNotNull(req.getAttribute("title"));
            baos = new ByteArrayOutputStream();
            IO.copy(new FileInputStream((File)req.getAttribute("title")), baos);
            assertEquals(getServletContext().getAttribute("title"), baos.toString());
            super.doPost(req, resp);
        }
