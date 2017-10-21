        public void handle(String target, org.eclipse.jetty.server.Request baseRequest,
                HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
        {
            if (baseRequest.isHandled())
            {
                return;
            }

            OutputStream out = null;

            if (baseRequest.getMethod().equals("PUT"))
            {
                baseRequest.setHandled(true);

                File file = new File(_resourcePath, URLDecoder.decode(request.getPathInfo(), "utf-8"));
                FS.ensureDirExists(file.getParentFile());

                out = new FileOutputStream(file);

                response.setStatus(HttpServletResponse.SC_CREATED);
            }

            if (baseRequest.getMethod().equals("POST"))
            {
                baseRequest.setHandled(true);
                out = new ByteArrayOutputStream();

                response.setStatus(HttpServletResponse.SC_OK);
            }

            if (out != null)
            {
                try (ServletInputStream in = request.getInputStream())
                {
                    IO.copy(in, out);
                }
                finally
                {
                    out.close();
                }

                if (!(out instanceof FileOutputStream))
                    _requestContent = out.toString();
            }
        }
