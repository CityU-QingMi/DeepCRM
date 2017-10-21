    public void handleOptions(FilterChain chain, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        chain.doFilter(request,new HttpServletResponseWrapper(response)
        {
            @Override
            public void setHeader(String name, String value)
            {
                if ("Allow".equalsIgnoreCase(name))
                {
                    Set<String> options = new HashSet<String>();
                    options.addAll(Arrays.asList(StringUtil.csvSplit(value)));
                    options.addAll(_operations);
                    value=null;
                    for (String o : options)
                        value=value==null?o:(value+", "+o);
                }

                super.setHeader(name,value);
            }
        });

    }
