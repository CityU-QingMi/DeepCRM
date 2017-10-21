        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            if(request.getAttribute("deep") == null)
            {
                AsyncContext ac = request.startAsync();
                ac.setTimeout(1000);
                ac.addListener(this);
                request.setAttribute("deep",true);
            }
        }
