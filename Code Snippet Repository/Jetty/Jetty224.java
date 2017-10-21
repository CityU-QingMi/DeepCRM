        private URI resolveURI(HttpRequest request, URI uri)
        {
            if (uri != null)
                return uri;
            String target = request.getScheme() + "://" + request.getHost();
            int port = request.getPort();
            if (port > 0)
                target += ":" + port;
            return URI.create(target);
        }
