        protected String rewriteTarget(HttpServletRequest request)
        {
            String path = request.getRequestURI();
            if (!path.startsWith(_prefix))
                return null;

            StringBuilder uri = new StringBuilder(_proxyTo);
            if (_proxyTo.endsWith("/"))
                uri.setLength(uri.length() - 1);
            String rest = path.substring(_prefix.length());
            if (!rest.isEmpty())
            {
                if (!rest.startsWith("/"))
                    uri.append("/");
                uri.append(rest);
            }

            String query = request.getQueryString();
            if (query != null)
            {
                // Is there at least one path segment ?
                String separator = "://";
                if (uri.indexOf("/", uri.indexOf(separator) + separator.length()) < 0)
                    uri.append("/");
                uri.append("?").append(query);
            }
            URI rewrittenURI = URI.create(uri.toString()).normalize();

            if (!proxyServlet.validateDestination(rewrittenURI.getHost(), rewrittenURI.getPort()))
                return null;

            return rewrittenURI.toString();
        }
