        private void sendResult(Session session, PrintWriter writer)
        {
            if (session != null)
            {
                if (session.getAttribute("a.b.c") != null)
                    writer.print("a.b.c/"+session.getAttribute("a.b.c"));    
                else
                    writer.print("-/0");
            }
            else
            {
                writer.print("0/0");
            }
        }
