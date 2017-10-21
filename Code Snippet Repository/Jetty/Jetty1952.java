    private void clearInputBuffer() 
    {
        try
        {
            while (System.in.available() > 0)
            {
                // System.in.skip doesn't work properly. I don't know why
                long available = System.in.available();
                for (int i = 0; i < available; i++)
                {
                    if (System.in.read() == -1)
                    {
                        break;
                    }
                }
            }
        }
        catch (IOException e)
        {
            mojo.getLog().warn("Error discarding console input buffer", e);
        }      
    }
