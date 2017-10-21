        private void sendResult(HttpSession session, PrintWriter writer)
        {
            if (session != null)
            {
                writer.print(session.getAttribute("test"));
            }
            else
            {
                writer.print("null");
            }
        }
