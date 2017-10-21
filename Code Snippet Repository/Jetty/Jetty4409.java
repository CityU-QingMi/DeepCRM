        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
        {
            ServletInputStream is = request.getInputStream();
            ReadLineInputStream rlis = new ReadLineInputStream(request.getInputStream());
            String line = "";
            while (line != null)
            {
                line = rlis.readLine();
            }
          chain.doFilter(request, response);
        }
