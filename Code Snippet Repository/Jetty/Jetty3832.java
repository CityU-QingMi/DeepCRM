        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            response.setStatus(200);
            response.getOutputStream().println("Hello world");
            response.getOutputStream().println("scheme='"+request.getScheme()+"'");
            response.getOutputStream().println("isSecure='"+request.isSecure()+"'");
            response.getOutputStream().println("X509Certificate='"+request.getAttribute("javax.servlet.request.X509Certificate")+"'");
            response.getOutputStream().println("cipher_suite='"+request.getAttribute("javax.servlet.request.cipher_suite")+"'");
            response.getOutputStream().println("key_size='"+request.getAttribute("javax.servlet.request.key_size")+"'");
            response.getOutputStream().println("ssl_session_id='"+request.getAttribute("javax.servlet.request.ssl_session_id")+"'");
            SSLSession sslSession=(SSLSession)request.getAttribute("SSL_SESSION");
            response.getOutputStream().println("ssl_session='"+sslSession+"'");
            
        }
