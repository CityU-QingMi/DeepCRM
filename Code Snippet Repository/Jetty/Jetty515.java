        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            try
            {
                String[] paths = target.split("/", 4);

                int status = Integer.parseInt(paths[1]);
                response.setStatus(status);

                String host = paths[2];
                String path = paths[3];
                boolean relative = Boolean.parseBoolean(request.getParameter("relative"));
                String location = relative ? "" : request.getScheme() + "://" + host + ":" + request.getServerPort();
                location += "/" + path;

                if (Boolean.parseBoolean(request.getParameter("decode")))
                    location = URLDecoder.decode(location, "UTF-8");

                response.setHeader("Location", location);

                if (Boolean.parseBoolean(request.getParameter("close")))
                    response.setHeader("Connection", "close");
            }
            catch (NumberFormatException x)
            {
                response.setStatus(200);
                // Echo content back
                IO.copy(request.getInputStream(), response.getOutputStream());
            }
            finally
            {
                baseRequest.setHandled(true);
            }
        }
