    private static String readResponse(Socket client) throws IOException
    {
        BufferedReader br=null;
        StringBuilder sb=new StringBuilder(1000);

        try
        {
            client.setSoTimeout(5000);
            br=new BufferedReader(new InputStreamReader(client.getInputStream()));

            String line;

            while ((line=br.readLine())!=null)
            {
                sb.append(line);
                sb.append('\n');
            }
        }
        catch(SocketTimeoutException e)
        {
            System.err.println("Test timedout: "+e.toString());
            e.printStackTrace(); // added to see if we can get more info from failures on CI
        }
        finally
        {
            if (br!=null)
            {
                br.close();
            }
        }
        return sb.toString();
    }
