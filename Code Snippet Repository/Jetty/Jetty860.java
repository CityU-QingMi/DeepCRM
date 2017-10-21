        @Override
        public void parsedHeader(HttpField httpField)
        {
            try
            {
                String name = httpField.getName();
                if ("Status".equalsIgnoreCase(name))
                {
                    if (!seenResponseCode)
                    {
                        seenResponseCode = true;

                        // Need to set the response status so the
                        // HttpParser can handle the content properly.
                        String value = httpField.getValue();
                        String[] parts = value.split(" ");
                        String status = parts[0];
                        int code = Integer.parseInt(status);
                        httpParser.setResponseStatus(code);
                        String reason = parts.length > 1 ? value.substring(status.length()) : HttpStatus.getMessage(code);

                        notifyBegin(code, reason.trim());
                        notifyHeaders(fields);
                    }
                }
                else
                {
                    fields.add(httpField);
                    if (seenResponseCode)
                        notifyHeader(httpField);
                }
            }
            catch (Throwable x)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Exception while invoking listener " + listener, x);
            }
        }
