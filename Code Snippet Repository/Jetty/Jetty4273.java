    public void init(FilterConfig config) throws ServletException
    {
        _context=config.getServletContext();

        _tmpdir=(File)_context.getAttribute("javax.servlet.context.tempdir");

        if (_context.getRealPath("/")==null)
           throw new UnavailableException("Packed war");

        String b = config.getInitParameter("baseURI");
        if (b != null)
        {
            _baseURI=b;
        }
        else
        {
            File base=new File(_context.getRealPath("/"));
            _baseURI=base.toURI().toString();
        }

        _delAllowed = getInitBoolean(config,"delAllowed");
        _putAtomic = getInitBoolean(config,"putAtomic");

        _operations.add(__OPTIONS);
        _operations.add(__PUT);
        if (_delAllowed)
        {
            _operations.add(__DELETE);
            _operations.add(__MOVE);
        }
    }
