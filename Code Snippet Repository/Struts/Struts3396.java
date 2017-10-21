    public void finishExecution() {
        HttpSession session = this.request.getSession();
        Enumeration attributeNames = session.getAttributeNames();

        MockHttpServletRequest nextRequest = new MockHttpServletRequest();

        while (attributeNames.hasMoreElements()) {
            String key = (String) attributeNames.nextElement();
            Object attribute = session.getAttribute(key);
            nextRequest.getSession().setAttribute(key, attribute);
        }

        this.response = new MockHttpServletResponse();
        this.request = nextRequest;
        this.pageContext = new MockPageContext(servletContext, request, response);
    }
