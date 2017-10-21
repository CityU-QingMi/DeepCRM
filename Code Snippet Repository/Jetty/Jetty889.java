    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        for (int i = 0; i < files.length - 1; ++i)
        {
            String file = files[i];
            String resolved = resolve(httpRequest, file);

            URL url = request.getServletContext().getResource(resolved);
            if (url == null)
                continue;

            if (Files.isReadable(toPath(url)))
            {
                chain.doFilter(httpRequest, httpResponse);
                return;
            }
        }

        // The last one is the fallback
        fallback(httpRequest, httpResponse, chain, files[files.length - 1]);
    }
