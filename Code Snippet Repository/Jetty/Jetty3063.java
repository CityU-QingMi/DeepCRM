    protected Writer getAcceptableWriter(Request baseRequest, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        List<String> acceptable=baseRequest.getHttpFields().getQualityCSV(HttpHeader.ACCEPT_CHARSET);
        if (acceptable.isEmpty())
        {
            response.setCharacterEncoding(StandardCharsets.ISO_8859_1.name());
            return response.getWriter();
        }
        
        for (String charset:acceptable)
        {
            try
            {
                if ("*".equals(charset))
                    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                else
                    response.setCharacterEncoding(Charset.forName(charset).name());
                return response.getWriter();
            }
            catch(Exception e)
            {
                LOG.ignore(e);
            }
        }
        return null;
    }
