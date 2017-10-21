        @Override
        public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp)
        {
            String query = req.getQueryString();

            // Start looking at the UpgradeRequest to determine what you want to do
            if ((query == null) || (query.length() <= 0))
            {
                try
                {
                    // Let UPGRADE request for websocket fail with
                    // status code 403 (FORBIDDEN) [per RFC-6455]
                    resp.sendForbidden("Unspecified query");
                }
                catch (IOException e)
                {
                    // An input or output exception occurs
                    e.printStackTrace();
                }

                // No UPGRADE
                return null;
            }

            // Create the websocket we want to
            if (query.contains("bigecho"))
            {
                return new BigEchoSocket();
            }
            else if (query.contains("echo"))
            {
                return new MyEchoSocket();
            }

            // Let UPGRADE fail with 503 (UNAVAILABLE)
            return null;
        }
