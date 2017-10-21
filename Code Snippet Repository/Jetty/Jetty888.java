        @Override
        protected void customize(Request request, HttpFields fastCGIHeaders)
        {
            super.customize(request, fastCGIHeaders);
            customizeFastCGIHeaders(request, fastCGIHeaders);
            if (_log.isDebugEnabled())
            {
                TreeMap<String, String> fcgi = new TreeMap<>();
                for (HttpField field : fastCGIHeaders)
                    fcgi.put(field.getName(), field.getValue());
                String eol = System.lineSeparator();
                _log.debug("FastCGI variables{}{}", eol, fcgi.entrySet().stream()
                        .map(entry -> String.format("%s: %s", entry.getKey(), entry.getValue()))
                        .collect(Collectors.joining(eol)));
            }
        }
