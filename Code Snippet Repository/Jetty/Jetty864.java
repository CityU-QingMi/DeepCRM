        @Override
        public boolean headerComplete()
        {
            if (!seenResponseCode)
            {
                // No Status header but we have other headers, assume 200 OK.
                notifyBegin(200, "OK");
                notifyHeaders(fields);
            }
            notifyHeaders();
            // Return from HTTP parsing so that we can parse the content.
            return true;
        }
